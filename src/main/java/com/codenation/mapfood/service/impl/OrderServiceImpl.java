package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.NoMotoboyInRangeException;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.*;
import com.codenation.mapfood.repository.OrderRepository;
import com.codenation.mapfood.repository.OrdersItemRepository;
import com.codenation.mapfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrdersItemRepository itemRepository;

    @Autowired
    MotoboyService motoboyService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ProductService productService;

    @Override
    public Orders registerOrder(Orders requestOrder) {
        Orders orders = fillOrder(requestOrder);
        Delivery delivery = deliveryService.orderCanBeAddedToAnExistingDelivery(orders);

        try {
            if (delivery != null) {
                updateExistingDelivery(orders, delivery);
            } else {
                createNewDelivery(orders);
            }
        }  catch (NoMotoboyInRangeException e) {
            //TODO
            System.out.println(e.getMessage());
            return null;
        }
        return orderRepository.save(orders);
    }

    @Override
    public Orders update(Orders order) {
        return orderRepository.save(order);
    }

    private void updateExistingDelivery(Orders order, Delivery delivery)  {
        delivery.getStops().add(new Stop(order.getCustomer().getCoordinates(), order));
        Delivery deliveryStored = deliveryService.update(delivery);
        order.setDelivery(deliveryStored);
        order.setInProgress(true);
        order.setStatus("IN PROGRESS");
    }

    private void createNewDelivery(Orders orders) throws NoMotoboyInRangeException {
        Delivery delivery;
        Motoboy motoboy = motoboyService.getNearest(orders.getRestaurant().getCoordinates());
        delivery = deliveryService.create(motoboy, orders.getRestaurant(), "IN PROGRESS");
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop(orders.getRestaurant().getCoordinates()));
        stops.add(new Stop(orders.getCustomer().getCoordinates(), orders));
        Delivery deliveryStored =
                insertSteps(delivery, motoboy.getCoordinates(), stops);
        orders.setDelivery(deliveryStored);
        orders.setInProgress(true);
        orders.setStatus("IN PROGRESS");
    }

    private Delivery insertSteps(Delivery delivery, Coordinates origin, List<Stop> stops) {
        delivery.setOrigin(origin);
        delivery.setStops(stops);
        return deliveryService.update(delivery);
    }

    public Orders fillOrder(Orders orders) {
        orders.setStatus("WAITING ANSWER");
        orders.setCreationDate(LocalDateTime.now());
        orders.setInProgress(false);

        try {
            Customer customer = customerService.findById(orders.getCustomer().getId());
            orders.setCustomer(customer);
        } catch (ResourceNotFoundException e) {
            //TODO
            System.out.println("Customer not found");
        }

        try {
            Restaurant restaurant = restaurantService.findById(orders.getRestaurant().getId());
            orders.setRestaurant(restaurant);
        } catch (ResourceNotFoundException e) {
            //TODO
            System.out.println("Restaurant not found");
        }

        List<OrdersItem> items = fillOrderItems(orders);
        orders.setItems(items);

        Double total = items.stream()
                .mapToDouble(item -> item.getProduct().getUnitPrice())
                .sum();

        orders.setOrderPrice(total);

        return orderRepository.save(orders);
    }

    private List<OrdersItem> fillOrderItems(Orders orders) {
        List<OrdersItem> items = new ArrayList<>();

        orders.getItems().forEach(item -> {
            try {
                Product product = productService.findById(item.getProduct().getId());

                if (!product.getRestaurant().getId().equals(orders.getRestaurant().getId())) {
                    //TODO: Trocar exception
                    throw new ResourceNotFoundException("Product doesnt belong to this restaurant");
                }

                item.setProduct(product);
                OrdersItem itemStored = itemRepository.save(item);

                items.add(itemStored);

            } catch (ResourceNotFoundException e) {
                System.out.println("Product not found");
            }
        });

        return items;
    }
}

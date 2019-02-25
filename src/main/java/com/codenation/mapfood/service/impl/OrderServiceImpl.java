package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.*;
import com.codenation.mapfood.repository.OrderRepository;
import com.codenation.mapfood.repository.OrdersItemRepository;
import com.codenation.mapfood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Long MAX_TIME_DIFFERENCE = 2l;
    private static final Double MAX_DISTANCE_DIFFERENCE = 5d;

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
        Delivery delivery = orderCanBeAddedToAnExistingDelivery(orders);

        if (delivery != null) {
            orders.setInProgress(true);
            orders.setDelivery(delivery);
            orders.setStatus("IN PROGRESS");
        } else {
            Motoboy motoboy = motoboyService.getNearest(orders.getRestaurant().getCoordinates());
            delivery = new Delivery(motoboy, orders.getRestaurant(), "IN PROGRESS");
            Delivery deliveryStored = deliveryService.add(delivery);
            orders.setDelivery(deliveryStored);
            orders.setStatus("IN PROGRESS");
        }

        return orderRepository.save(orders);
    }

    private Delivery orderCanBeAddedToAnExistingDelivery(Orders orders) {
        List<Delivery> deliveryList = deliveryService.getRestaurantInProgressOrders(orders.getRestaurant());
        Iterator<Delivery> it = deliveryList.iterator();

        while (it.hasNext() && (orders.getDelivery() != null)) {
            Delivery delivery = it.next();
            if (delivery.isAvaliableToNewOrders()) {
                Iterator<Orders> ordersIterator = delivery.getOrders().iterator();
                while (ordersIterator.hasNext() && (orders.getDelivery() != null)) {
                    Orders o = ordersIterator.next();

                    Double distanceBetweenCustomers =
                            orders.getCustomer().getCoordinates().distanceFrom(o.getCustomer().getCoordinates());

                    Long ordersTimeDifference = ChronoUnit.MINUTES.between(orders.getCreationDate(), o.getCreationDate());

                    if (ordersTimeDifference <= MAX_TIME_DIFFERENCE &&
                            distanceBetweenCustomers <= MAX_DISTANCE_DIFFERENCE) {
                        return delivery;
                    }
                }
            }
        }

        return null;
    }

    public Orders fillOrder(Orders orders) {
        orders.setStatus("WAITING ANSWER");
        orders.setCreationDate(LocalDate.now());
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

        return orders;
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

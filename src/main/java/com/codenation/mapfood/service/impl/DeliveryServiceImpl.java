package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.DeliveryRepository;
import com.codenation.mapfood.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private static final Long MAX_TIME_DIFFERENCE = 2L;
    private static final Double MAX_DISTANCE_DIFFERENCE = 5000D;

    @Autowired
    DeliveryRepository repository;

    @Override
    public List<Delivery> getRestaurantInProgressOrders(Restaurant restaurant) {
        return repository.findByRestaurantIdAndStatus(restaurant.getId(), "IN PROGRESS");
    }

    @Override
    public Delivery add(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    public Delivery create(Motoboy motoboy, Restaurant restaurant, String status) {
        Delivery delivery = new Delivery(motoboy, restaurant, status);
        Delivery storedDelivery = add(delivery);

        return storedDelivery;
    }

    @Override
    public Delivery orderCanBeAddedToAnExistingDelivery(Orders newOrder) {
        List<Delivery> list = getRestaurantInProgressOrders(newOrder.getRestaurant());
        Iterator<Delivery> deliveryIt = list.iterator();

        while (deliveryIt.hasNext()) {
            Delivery delivery = deliveryIt.next();
            if (delivery.isAvaliableToNewOrders()) {
                Iterator<Orders> orderIt = delivery.getOrders().iterator();
                while (orderIt.hasNext() ) {
                    Orders deliveryOrder = orderIt.next();

                    Double distanceBetweenCustomers =
                            newOrder.getCustomer().getCoordinates().distanceFrom(deliveryOrder.getCustomer().getCoordinates());

                    Long ordersTimeDifference = Duration.between(deliveryOrder.getCreationDate(), newOrder.getCreationDate()).toMinutes();

                    if (ordersTimeDifference <= MAX_TIME_DIFFERENCE &&
                            distanceBetweenCustomers <= MAX_DISTANCE_DIFFERENCE) {
                        return delivery;
                    }
                }
            }
        }

        return null;
    }
}

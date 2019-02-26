package com.codenation.mapfood.service;

import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.model.Restaurant;

import java.util.List;

public interface DeliveryService {

    List<Delivery> getRestaurantInProgressOrders(Restaurant restaurant);

    Delivery add(Delivery delivery);

    Delivery create(Motoboy motoboy, Restaurant restaurant, String status);

    Delivery orderCanBeAddedToAnExistingDelivery(Orders order);

    List<Route> getDeliveryRoute(Long id) throws ResourceNotFoundException;
}

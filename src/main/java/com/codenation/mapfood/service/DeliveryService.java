package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Restaurant;

import java.util.List;

public interface DeliveryService {

    List<Delivery> getRestaurantInProgressOrders(Restaurant restaurant);

    Delivery add(Delivery delivery);
}

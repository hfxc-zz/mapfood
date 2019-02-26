package com.codenation.mapfood.service;


import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.model.*;


import java.util.List;

public interface DeliveryService {

    List<Delivery> getRestaurantInProgressOrders(Restaurant restaurant);

    Delivery update(Delivery delivery);

    Delivery create(Motoboy motoboy, Restaurant restaurant, String status);

    Delivery stopVisited(Long id, Stop stop, Double distanceTravlled) throws ResourceNotFoundException;

    Delivery orderCanBeAddedToAnExistingDelivery(Orders order);

    List<Route> getDeliveryRoute(Long id) throws ResourceNotFoundException;

    Delivery getById(Long id) throws ResourceNotFoundException;

    List<Delivery> getDeliveriesByRestaurantId(Long id);
}

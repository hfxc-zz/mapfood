package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.DeliveryRepository;
import com.codenation.mapfood.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hfxc on 24/02/19.
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository repository;

    @Override
    public List<Delivery> getRestaurantInProgressOrders(Restaurant restaurant) {
        return repository.findByRestaurantAndStatus(restaurant, "IN PROGRESS");
    }

    @Override
    public Delivery add(Delivery delivery) {
        return repository.save(delivery);
    }
}

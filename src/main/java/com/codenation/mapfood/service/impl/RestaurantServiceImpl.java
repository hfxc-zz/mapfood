package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hfxc on 24/02/19.
 */
public class RestaurantServiceImpl {

    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}

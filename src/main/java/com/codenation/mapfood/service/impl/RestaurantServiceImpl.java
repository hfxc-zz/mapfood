package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.RestaurantRepository;
import com.codenation.mapfood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;

    public Restaurant findById(Long id) throws ResourceNotFoundException {
        Optional<Restaurant> restaurant = repository.findById(id);
        return restaurant.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }
}
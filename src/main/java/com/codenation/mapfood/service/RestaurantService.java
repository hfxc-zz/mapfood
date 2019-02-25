package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.RestaurantRepository;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by hfxc on 24/02/19.
 */
@Service
public class RestaurantService {

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

package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.repository.RestaurantRepository;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Restaurant findById(Long id) throws ResourceNotFoundException;

    List<Restaurant> findAll();
}

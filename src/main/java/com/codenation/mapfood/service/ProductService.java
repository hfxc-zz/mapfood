package com.codenation.mapfood.service;

import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id) throws ResourceNotFoundException;

    List<Product> findByRestaurantId(Long restaurantId);

    List<Product> findByDescription(String description);
}

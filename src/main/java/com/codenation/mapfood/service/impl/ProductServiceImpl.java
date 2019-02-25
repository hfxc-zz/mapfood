package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Product;
import com.codenation.mapfood.repository.ProductRepository;
import com.codenation.mapfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) throws ResourceNotFoundException {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Product> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Product> findByDescription(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }
}

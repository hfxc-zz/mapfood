package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDescriptionContainingIgnoreCase(String description);
    List<Product> findByRestaurantId(Long restaurantId);
}

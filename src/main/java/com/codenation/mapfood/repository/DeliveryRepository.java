package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByRestaurantIdAndStatus(Long id, String status);
}

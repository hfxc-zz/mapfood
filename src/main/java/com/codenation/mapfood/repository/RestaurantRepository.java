package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hfxc on 24/02/19.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

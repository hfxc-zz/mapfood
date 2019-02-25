package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hfxc on 25/02/19.
 */
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
    City findByNameContainingIgnoreCase(String name);
}

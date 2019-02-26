package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
    City findByNameContainingIgnoreCase(String name);
}

package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    List<Motoboy>
    findByCoordinates_LongitudeBetweenAndCoordinates_LatitudeBetween(Double minLon, Double maxLon, Double minLat, Double maxLat);
}
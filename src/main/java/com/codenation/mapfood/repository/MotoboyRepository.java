package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {
}

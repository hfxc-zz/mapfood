package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    @Autowired
    MotoboyRepository repository;

    public List<Motoboy> getAll() {
        return repository.findAll();
    }


    public Motoboy getNearest(Coordinates coordinates) {
        return getAll().stream().
                min(Comparator.comparing(m -> coordinates.distanceFrom(m.getCoordinates()))).get();
    }
}

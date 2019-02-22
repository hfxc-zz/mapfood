package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Motoboy;

import java.util.List;

public interface MotoboyService {

    List<Motoboy> getAll();

    Motoboy getNearest(Coordinates coordinates);
}

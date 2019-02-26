package com.codenation.mapfood.service;

import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.model.Coordinates;

import java.util.List;

public interface MapService {
    List<Route> getRoutes(Coordinates origin, Coordinates destination);
    List<Route> getRoutes(Coordinates origin, Coordinates destination, List<Coordinates> stops);
}

package com.codenation.mapfood.client;

import com.google.maps.model.DirectionsRoute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Route {
    private List<RouteLeg> legs;

    public Route(DirectionsRoute route) {
        this.legs = Arrays.stream(route.legs)
                          .map(RouteLeg::new)
                          .collect(Collectors.toList());
    }

    public List<RouteLeg> getLegs() {
        return legs;
    }

    public void setLegs(List<RouteLeg> legs) {
        this.legs = legs;
    }
}

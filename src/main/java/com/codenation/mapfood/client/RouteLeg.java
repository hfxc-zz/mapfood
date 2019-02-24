package com.codenation.mapfood.client;

import com.google.maps.model.DirectionsLeg;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteLeg {
    private Long distance;
    private Long duration;
    private List<RouteStep> steps;

    public RouteLeg(DirectionsLeg leg) {
        this.distance = leg.distance.inMeters;
        this.duration = leg.duration.inSeconds;
        this.steps = Arrays.stream(leg.steps)
                           .map(RouteStep::new)
                           .collect(Collectors.toList());
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<RouteStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RouteStep> steps) {
        this.steps = steps;
    }
}

package com.codenation.mapfood.client;

import com.google.maps.model.DirectionsStep;

public class RouteStep {
    private Long distance;
    private Long duration;
    private String instruction;

    public RouteStep(DirectionsStep step) {
        this.distance = step.distance.inMeters;
        this.duration = step.duration.inSeconds;
        this.instruction = step.htmlInstructions;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}

package com.codenation.mapfood.client;

import com.google.maps.model.DirectionsStep;

public class RouteStep {
    private String distance;
    private Long distanceInMeters;
    private String duration;
    private Long durationInSeconds;
    private String instruction;

    public RouteStep(DirectionsStep step) {
        this.distance = step.distance.humanReadable;
        this.distanceInMeters = step.distance.inMeters;
        this.duration = step.duration.humanReadable;
        this.durationInSeconds = step.duration.inSeconds;
        this.instruction = removeHtmlTags(step.htmlInstructions);
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Long distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = removeHtmlTags(instruction);
    }

    private String removeHtmlTags(String text) {
        return text.replaceAll("<(.|\\n)+?>", "");
    }
}
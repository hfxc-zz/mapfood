package com.codenation.mapfood.client;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;

import java.util.List;

public class RouteInformation {
    private LatLng src;
    private List<LatLng> dests;
    private Duration duration;
    private Distance distance;

    public LatLng getSrc() {
        return src;
    }

    public void setSrc(LatLng src) {
        this.src = src;
    }

    public List<LatLng> getDests() {
        return dests;
    }

    public void setDests(List<LatLng> dests) {
        this.dests = dests;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}

package com.codenation.mapfood.dto;

import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Stop;

public class StopDTO {

    private Long id;
    private Coordinates coordinates;
    private Long orderId;
    private Boolean visited;
    private Double distanceTravelled;

    public StopDTO() {
    }

    public StopDTO(Stop stop) {
        this.id = stop.getId();
        this.coordinates = stop.getCoordinates();
        this.visited = stop.isVisited();
        if(stop.getOrder() != null) {
            this.orderId = stop.getOrder().getId();
        }
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(Double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

package com.codenation.mapfood.dto;

import com.codenation.mapfood.model.Delivery;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryDTO {
    List<StopDTO> stops;
    Long motoboyId;
    Long restaurantId;
    Double travelledDistance;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime finishDate;
    private String status;

    public DeliveryDTO() {
    }

    public DeliveryDTO(Delivery delivery) {
        this.motoboyId = delivery.getMotoboy().getId();
        this.restaurantId = delivery.getRestaurant().getId();
        this.travelledDistance = delivery.getTravelledDistance();
        this.creationDate = delivery.getCreationDate();
        this.finishDate = delivery.getFinishDate();
        this.status = delivery.getStatus();
        this.stops = delivery.getStops().stream().map(StopDTO::new).collect(Collectors.toList());
    }

    public List<StopDTO> getStops() {
        return stops;
    }

    public void setStops(List<StopDTO> stops) {
        this.stops = stops;
    }

    public Long getMotoboyId() {
        return motoboyId;
    }

    public void setMotoboyId(Long motoboyId) {
        this.motoboyId = motoboyId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

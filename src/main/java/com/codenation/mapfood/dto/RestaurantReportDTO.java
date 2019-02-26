package com.codenation.mapfood.dto;

import com.codenation.mapfood.model.Delivery;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantReportDTO {

    private Double timePerDistance_h_per_Km;
    private List<DeliveryDTO> deliveries;

    public RestaurantReportDTO(List<Delivery> deliveries) {
        this.deliveries = deliveries.stream().map(DeliveryDTO::new).collect(Collectors.toList());
        double totalTimeInHours = 0;
        double totalDistanceTavelled = 0;
        for(Delivery d : deliveries) {
            if(d.getCreationDate() != null && d.getCreationDate() != null
                && d.getTravelledDistance() != null) {
                double minutes =  d.getCreationDate().until(d.getFinishDate(), ChronoUnit.MINUTES);
                totalTimeInHours += minutes/60D;
                totalDistanceTavelled += d.getTravelledDistance()/10e3D;
            }
        }
        timePerDistance_h_per_Km = totalTimeInHours/totalDistanceTavelled;
    }

    public Double getTimePerDistance_h_per_Km() {
        return timePerDistance_h_per_Km;
    }

    public void setTimePerDistance_h_per_Km(Double timePerDistance_h_per_Km) {
        this.timePerDistance_h_per_Km = timePerDistance_h_per_Km;
    }

    public List<DeliveryDTO> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDTO> deliveries) {
        this.deliveries = deliveries;
    }
}

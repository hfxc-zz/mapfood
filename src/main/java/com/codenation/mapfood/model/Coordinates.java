package com.codenation.mapfood.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Coordinates {
    private Double longitude;
    private Double latitude;
    private static final double R = 6371e3D;

    Coordinates() {
    }

    public Coordinates(String longitude, String latitude) {
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double distanceFrom(Coordinates coordinate) {
        //Haversine formula
        double lat1 = Math.toRadians(coordinate.latitude);
        double lat2 = Math.toRadians(this.latitude);
        double dLat = lat2 - lat1;
        double dLong = Math.toRadians(this.longitude - coordinate.longitude);

        double a = Math.sin(dLat/2D) * Math.sin(dLat/2D) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLong/2D) * Math.sin(dLong/2D);

        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D-a));

        return R*c;
    }
}

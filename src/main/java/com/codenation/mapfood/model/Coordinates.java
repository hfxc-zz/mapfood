package com.codenation.mapfood.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.MathContext;

@Embeddable
public class Coordinates {
    private BigDecimal longitude;
    private BigDecimal latitude;
    private static final double R = 6371e3D;

    Coordinates() {
    }

    Coordinates(String longitude, String latitude) {
        MathContext mathContext = new MathContext(8);

        this.longitude = new BigDecimal(longitude, mathContext);
        this.latitude = new BigDecimal(latitude, mathContext);
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Double distanceFrom(Coordinates coordinate) {
        //Haversine formula
        double lat1 = Math.toRadians(coordinate.latitude.doubleValue());
        double lat2 = Math.toRadians(this.latitude.doubleValue());
        double dLat = lat2 - lat1;
        double dLong = Math.toRadians(this.longitude.subtract(coordinate.longitude).doubleValue());

        double a = Math.sin(dLat/2D) * Math.sin(dLat/2D) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLong/2D) * Math.sin(dLong/2D);

        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D-a));

        return R*c;
    }
}

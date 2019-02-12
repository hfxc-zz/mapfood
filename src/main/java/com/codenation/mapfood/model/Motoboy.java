package com.codenation.mapfood.model;

import java.math.BigDecimal;

public class Motoboy {

    private int id;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}

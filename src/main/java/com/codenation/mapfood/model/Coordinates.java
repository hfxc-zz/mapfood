package com.codenation.mapfood.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.MathContext;

@Embeddable
public class Coordinates {
    private BigDecimal longitude;
    private BigDecimal latitude;

    Coordinates(String longitude, String latitude) {
        MathContext mathContext = new MathContext(8);

        this.longitude = new BigDecimal(longitude, mathContext);
        this.latitude = new BigDecimal(latitude, mathContext);
    }
}

package com.codenation.mapfood.model;

import javax.persistence.*;

@Entity
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_type_seq")
    @SequenceGenerator(name = "restaurant_type_seq", sequenceName = "restaurant_type_seq", allocationSize = 1)
    private Long id;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

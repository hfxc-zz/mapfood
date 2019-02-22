package com.codenation.mapfood.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    private String id;

    private String name;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne
    private RestaurantType type;

    @ManyToOne
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="motoboy")
    private List<Orders> orders;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public void addOrder(Orders orders) {
        if(this.orders == null) {
            this.orders = new ArrayList<Orders>();
        }
        this.orders.add(orders);
    }
}

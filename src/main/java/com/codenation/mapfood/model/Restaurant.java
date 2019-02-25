package com.codenation.mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    @SequenceGenerator(name = "restaurant_seq", sequenceName = "restaurant_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Embedded
    private Coordinates coordinates;

    private String description;

    @ManyToOne
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="restaurant")
    @JsonIgnore
    private List<Orders> orders;

    public Restaurant() {
    }

    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant(Long id, Coordinates coordinates, String name, String description, City city) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
        this.city = city;
    }

    public Restaurant(String name, City city, String longitude, String latitude, String description, String hashId) {
        this.name = name;
        this.coordinates = new Coordinates(longitude, latitude);
        this.description = description;
        this.city = city;
        this.hashId = hashId;
    }

    private String hashId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }
}

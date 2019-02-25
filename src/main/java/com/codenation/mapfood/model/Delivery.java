package com.codenation.mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    @SequenceGenerator(name = "delivery_seq", sequenceName = "delivery_seq", allocationSize = 1)
    private Long id;

    @Size(max = 5)
    @OneToMany(fetch = FetchType.LAZY, mappedBy="delivery")
    @JsonIgnore
    private List<Orders> orders;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="latitude", column = @Column(name="origin_lat") ),
            @AttributeOverride(name="longitude", column = @Column(name="origin_lon") ),
    })
    private Coordinates origin;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="latitude", column = @Column(name="dest_lat") ),
            @AttributeOverride(name="longitude", column = @Column(name="dest_lon") ),
    })
    private Coordinates destination;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private List<Stop> stops;

    @ManyToOne
    @JoinColumn(name = "motoboy_id")
    private Motoboy motoboy;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String status;

    public Delivery() {
    }

    public Delivery(Motoboy motoboy, Restaurant restaurant, String status) {
        this.motoboy = motoboy;
        this.restaurant = restaurant;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public void setOrigin(Coordinates origin) {
        this.origin = origin;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public void setDestination(Coordinates destination) {
        this.destination = destination;
    }

    public Motoboy getMotoboy() {
        return motoboy;
    }

    public void setMotoboy(Motoboy motoboy) {
        this.motoboy = motoboy;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvaliableToNewOrders() {
        return (this.orders != null) && (this.orders.size() < 5);
    }
}

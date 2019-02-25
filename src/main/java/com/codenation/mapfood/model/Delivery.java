package com.codenation.mapfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by hfxc on 24/02/19.
 */
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

    @Size(max = 5)
    @ManyToMany
    @JoinTable(name = "delivery_customers",
            joinColumns = {@JoinColumn(name = "delivery_id") },
            inverseJoinColumns = {@JoinColumn(name = "customer_id") })
    private List<Customer> customers;

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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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

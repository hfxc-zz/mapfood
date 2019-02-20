package com.codenation.mapfood.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    private Long id;
    @ManyToMany
    private List<Product> productList;
    private boolean inProgress;

    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Motoboy motoboy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Motoboy getMotoboy() {
        return motoboy;
    }

    public void setMotoboy(Motoboy motoboy) {
        this.motoboy = motoboy;
    }
}

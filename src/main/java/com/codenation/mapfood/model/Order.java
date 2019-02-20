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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESTAURANT_ID")
    private Restaurant restaurant;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MOTOBOY_ID")
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

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.addOrder(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.addOrder(this);
    }

    public Motoboy getMotoboy() {
        return motoboy;
    }

    public void setMotoboy(Motoboy motoboy) {
        this.motoboy = motoboy;
        motoboy.addOrder(this);
    }
}

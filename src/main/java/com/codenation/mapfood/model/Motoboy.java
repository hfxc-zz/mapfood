package com.codenation.mapfood.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Motoboy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motoboy_seq")
    @SequenceGenerator(name = "motoboy_seq", sequenceName = "motoboy_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy="motoboy")
    private List<Order> orders;

    @Embedded
    private Coordinates coordinates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if(orders == null) {
            orders = new ArrayList<Order>();
        }
        orders.add(order);
    }
}

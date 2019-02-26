package com.codenation.mapfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OrdersItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_item_seq")
    @SequenceGenerator(name = "delivery_item_seq", sequenceName = "delivery_item_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private Integer ammount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }
}

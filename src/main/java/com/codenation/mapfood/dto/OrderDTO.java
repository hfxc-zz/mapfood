package com.codenation.mapfood.dto;

import com.codenation.mapfood.client.Route;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Long customerId;
    private List<Long> productIdList;
    private String restaurantName;
    private LocalDateTime orderDate;
    private Long deliveryId;
    private String status;
    private List<Route> routes;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Long customerId, List<Long> productIdList, String restaurantName,
                    LocalDateTime orderDate, Long deliveryId, String status, List<Route> routes) {
        this.id = id;
        this.customerId = customerId;
        this.productIdList = productIdList;
        this.restaurantName = restaurantName;
        this.orderDate = orderDate;
        this.deliveryId = deliveryId;
        this.status = status;
        this.routes = routes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}

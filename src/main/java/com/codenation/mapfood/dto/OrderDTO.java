package com.codenation.mapfood.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hfxc on 25/02/19.
 */
public class OrderDTO {

    private Long id;
    private Long customerId;
    private List<Long> productIdList;
    private String restaurantName;
    private LocalDateTime orderDate;
    private Long deliveryId;
    private String status;

    public OrderDTO(Long id, Long customerId, List<Long> productIdList, String restaurantName,
                    LocalDateTime orderDate, Long deliveryId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.productIdList = productIdList;
        this.restaurantName = restaurantName;
        this.orderDate = orderDate;
        this.deliveryId = deliveryId;
        this.status = status;
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
}

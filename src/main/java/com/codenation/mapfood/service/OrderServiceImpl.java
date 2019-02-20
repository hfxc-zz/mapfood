package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Order;
import com.codenation.mapfood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;

    @Override
    public Order submitOrder(Order order) {
        return null;
    }
}

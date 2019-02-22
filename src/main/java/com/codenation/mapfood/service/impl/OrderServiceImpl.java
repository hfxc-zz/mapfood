package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Customer;
import com.codenation.mapfood.model.Motoboy;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.repository.OrderRepository;
import com.codenation.mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    MotoboyService motoboyService;
    @Autowired
    CustomerService customerService;

    public Orders submitOrder(Orders orders) {
        Customer customer = customerService.getById(orders.getCustomer().getId());
        Motoboy motoboy = motoboyService.getNearest(customer.getCoordinates());
        orders.setMotoboy(motoboy);
        orders.setInProgress(true);
        return repository.save(orders);
    }
}

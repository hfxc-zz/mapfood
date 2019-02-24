package com.codenation.mapfood.controller;

import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping
    public @ResponseBody
    Orders submitOrder(@RequestBody Orders orders) {
        return service.submitOrder(orders);
    }

}

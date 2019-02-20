package com.codenation.mapfood.controller;

import com.codenation.mapfood.model.Order;
import com.codenation.mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping
    public @ResponseBody
    Order submitOrder(@RequestBody Order order) {
        return service.submitOrder(order);
    }


}

package com.codenation.mapfood.controller;

import com.codenation.mapfood.dto.OrderDTO;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping
    public OrderDTO register(@Valid @RequestBody Orders order) {
        Orders storedOrder = service.registerOrder(order);
        List<Long> productIdList = new ArrayList<>();
        storedOrder.getItems().forEach(item -> {
            productIdList.add(item.getProduct().getId());
        });

        OrderDTO response = new OrderDTO(storedOrder.getId(), storedOrder.getCustomer().getId(),
                productIdList, storedOrder.getRestaurant().getName(), storedOrder.getCreationDate(),
                storedOrder.getDelivery().getId(), storedOrder.getStatus());

        return response;
    }
}

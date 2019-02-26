package com.codenation.mapfood.controller;

import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.dto.OrderDTO;
import com.codenation.mapfood.model.Delivery;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.model.Stop;
import com.codenation.mapfood.service.MapService;
import com.codenation.mapfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    MapService mapService;

    @PostMapping
    public OrderDTO register(@Valid @RequestBody Orders order) {
        Orders storedOrder = service.registerOrder(order);
        List<Long> productIdList = new ArrayList<>();
        storedOrder.getItems().forEach(item -> {
            productIdList.add(item.getProduct().getId());
        });

        Delivery delivery = storedOrder.getDelivery();
        List<Route> routes = mapService.getRoutes(delivery.getOrigin(), delivery.getDestination(),
                delivery.getStops().stream()
                        .map(Stop::getCoordinates)
                        .collect(Collectors.toList()));

        OrderDTO response = new OrderDTO(storedOrder.getId(), storedOrder.getCustomer().getId(),
                productIdList, storedOrder.getRestaurant().getName(), storedOrder.getCreationDate(),
                storedOrder.getDelivery().getId(), storedOrder.getStatus(), routes);

        return response;
    }
}

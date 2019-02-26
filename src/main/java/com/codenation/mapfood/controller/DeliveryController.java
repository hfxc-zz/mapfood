package com.codenation.mapfood.controller;

import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService service;

    @GetMapping("{id}/route")
    public List<Route> getDeliveryRoute(@PathVariable Long id) throws ResourceNotFoundException {
        return service.getDeliveryRoute(id);
    }
}

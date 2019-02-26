package com.codenation.mapfood.controller;

import com.codenation.mapfood.dto.RestaurantReportDTO;
import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @GetMapping
    public List<Restaurant> listAll() {
        return service.findAll();
    }

    @GetMapping("report/{id}")
    public RestaurantReportDTO report(@PathVariable Long id) {
        return new RestaurantReportDTO(service.getRestaurantDeliveries(id));
    }
}

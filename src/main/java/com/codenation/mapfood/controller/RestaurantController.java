package com.codenation.mapfood.controller;

import com.codenation.mapfood.model.Restaurant;
import com.codenation.mapfood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @GetMapping
    public List<Restaurant> listAll() {
        return service.findAll();
    }
}

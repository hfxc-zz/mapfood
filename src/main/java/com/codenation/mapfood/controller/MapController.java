package com.codenation.mapfood.controller;

import com.codenation.mapfood.client.Route;
import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Coordinates;
import com.codenation.mapfood.model.Customer;
import com.codenation.mapfood.service.CustomerService;
import com.codenation.mapfood.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    MapService service;
    @Autowired
    CustomerService customerService;

    //just for testing
    @GetMapping("/{id1}/{id2}/{id3}/{id4}")
    public List<Route> getRoute(@PathVariable Long id1, @PathVariable Long id2
                                , @PathVariable Long id3, @PathVariable Long id4) {
        try {
            Customer customer1 = customerService.findById(id1);
            Customer customer2 = customerService.findById(id2);
            Customer customer3 = customerService.findById(id3);
            Customer customer4 = customerService.findById(id4);
            List<Coordinates> stops = Arrays.asList(customer3.getCoordinates(), customer4.getCoordinates());
            return service.getRoutes(customer1.getCoordinates(), customer2.getCoordinates(), stops);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}


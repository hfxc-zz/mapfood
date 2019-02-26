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
import com.codenation.mapfood.dto.DeliveryDTO;
import com.codenation.mapfood.dto.StopDTO;
import com.codenation.mapfood.model.Orders;
import com.codenation.mapfood.model.Stop;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deliveries")
public class DeliveryController {

    @Autowired
    DeliveryService service;

    @GetMapping("{id}/route")
    public List<Route> getDeliveryRoute(@PathVariable Long id) throws ResourceNotFoundException {
        return service.getDeliveryRoute(id);
    }

    @PutMapping("/{id}")
    public DeliveryDTO stopVisited(@PathVariable Long id, @RequestBody StopDTO stopDTO) {
        try {
            Stop stop = new Stop(stopDTO.getCoordinates(), new Orders(stopDTO.getOrderId()));
            stop.setId(stopDTO.getId());
            return new DeliveryDTO(service.stopVisited(id, stop, stopDTO.getDistanceTravelled()));
        } catch (ResourceNotFoundException e) {
            //TODO
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public DeliveryDTO get(@PathVariable Long id) {
        try {
            return new DeliveryDTO(service.getById(id));
        } catch (ResourceNotFoundException e) {
            //TODO
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}

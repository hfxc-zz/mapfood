package com.codenation.mapfood.controller;

import com.codenation.mapfood.model.Product;
import com.codenation.mapfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hfxc on 25/02/19.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/filter")
    public List<Product> findByDescription(@RequestParam String description) {
        return service.findByDescription(description);
    }
}

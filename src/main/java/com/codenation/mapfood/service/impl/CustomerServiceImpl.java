package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.model.Customer;
import com.codenation.mapfood.repository.CustomerRepository;
import com.codenation.mapfood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;

    @Override
    public Customer getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}

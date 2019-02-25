package com.codenation.mapfood.service.impl;

import com.codenation.mapfood.exception.ResourceNotFoundException;
import com.codenation.mapfood.model.Customer;
import com.codenation.mapfood.repository.CustomerRepository;
import com.codenation.mapfood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;

    @Override
    public Customer findById(Long id) throws ResourceNotFoundException {
        Optional<Customer> customer = repository.findById(id);
        return customer.orElseThrow(ResourceNotFoundException::new);
    }
}

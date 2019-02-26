package com.codenation.mapfood.service;

import com.codenation.mapfood.model.Customer;
import com.codenation.mapfood.exception.ResourceNotFoundException;

public interface CustomerService {
    Customer findById(Long id) throws ResourceNotFoundException;
}

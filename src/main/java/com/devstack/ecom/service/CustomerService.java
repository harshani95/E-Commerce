package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestCustomerDto;
import com.devstack.ecom.dto.response.ResponseCustomerDto;

public interface CustomerService {
    public void create(RequestCustomerDto customerDto);
    public ResponseCustomerDto findById(String id);
    public void update(String id,RequestCustomerDto customerDto);
    public void delete(String id);
}

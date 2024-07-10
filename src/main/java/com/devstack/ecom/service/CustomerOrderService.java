package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestCustomerOrderDto;
import com.devstack.ecom.dto.response.ResponseCustomerOrderDto;
import com.devstack.ecom.dto.response.paginate.CustomerOrderPaginateDto;

public interface CustomerOrderService {
    public void create(RequestCustomerOrderDto customerOrderDto);
    public ResponseCustomerOrderDto findById(String id);
    public void update(String id,RequestCustomerOrderDto customerOrderDto);
    public CustomerOrderPaginateDto findAll(String customerId, int page, int size);
    public void delete(String id);
}

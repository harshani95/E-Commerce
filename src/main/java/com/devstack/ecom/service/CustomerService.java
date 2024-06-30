package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestCustomerDto;
import com.devstack.ecom.dto.response.ResponseCustomerDto;
import com.devstack.ecom.dto.response.paginate.CustomerPaginateDto;

public interface CustomerService {
    public void create(RequestCustomerDto customerDto);
    public ResponseCustomerDto findById(String id);
    public void update(String id,RequestCustomerDto customerDto);
    public void delete(String id);
    public CustomerPaginateDto findAll(String searchText, int page, int size);
}

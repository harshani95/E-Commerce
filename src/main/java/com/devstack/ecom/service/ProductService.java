package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestProductDto;
import com.devstack.ecom.dto.response.ResponseProductDto;
import com.devstack.ecom.dto.response.paginate.ProductPaginateDto;

public interface ProductService {
    public void create(RequestProductDto productDto);
    public ResponseProductDto findById(String id);
    public void update(String id,RequestProductDto productDto);
    public ProductPaginateDto findAll(String searchText, int page, int size);
    public void delete(String id);
}

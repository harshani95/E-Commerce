package com.devstack.ecom.service.impl;

import com.devstack.ecom.dto.request.RequestProductDto;
import com.devstack.ecom.dto.response.ResponseProductDto;
import com.devstack.ecom.dto.response.paginate.ProductPaginateDto;
import com.devstack.ecom.repo.ProductRepo;
import com.devstack.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public void create(RequestProductDto productDto) {

    }

    @Override
    public ResponseProductDto findById(String id) {
        return null;
    }

    @Override
    public void update(String id, RequestProductDto productDto) {

    }

    @Override
    public ProductPaginateDto findAll(String searchText, int page, int size) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

package com.devstack.ecom.service.impl;

import com.devstack.ecom.dto.request.RequestProductDto;
import com.devstack.ecom.dto.response.ResponseProductDto;
import com.devstack.ecom.dto.response.ResponseProductImageDto;
import com.devstack.ecom.dto.response.paginate.ProductPaginateDto;
import com.devstack.ecom.entity.Product;
import com.devstack.ecom.entity.ProductImage;
import com.devstack.ecom.exception.EntryNotFoundException;
import com.devstack.ecom.repo.ProductRepo;
import com.devstack.ecom.service.ProductService;
import com.devstack.ecom.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final FileDataExtractor dataExtractor;

    @Override
    public void create(RequestProductDto productDto) {
        Product product = Product.builder()
                .description(productDto.getDescription())
                .qty(productDto.getQty())
                .unitPrice(productDto.getUnitPrice())
                .propertyId(UUID.randomUUID().toString())
                .build();
        productRepo.save(product);
    }

    @Override
    public ResponseProductDto findById(String id) {
        Optional<Product> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product not found");
        }
        return createResponseProductDto(selectedProduct.get());
    }

    @Override
    public void update(String id, RequestProductDto productDto) {
        Optional<Product> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product not found");
        }

        Product product = Product.builder()
                .description(productDto.getDescription())
                .qty(productDto.getQty())
                .unitPrice(productDto.getUnitPrice())
                .propertyId(id)
                .build();
        productRepo.save(product);
    }

    @Override
    public ProductPaginateDto findAll(String searchText, int page, int size) {
        return ProductPaginateDto.builder()
                .dataList(productRepo.findAllWithSearchText(searchText, PageRequest.of(page, size))
                        .stream().map(this::createResponseProductDto).toList())
                .count(
                        productRepo.countAllWithSearchText(searchText)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        productRepo.deleteById(id);
    }


    private ResponseProductDto createResponseProductDto(Product product) {

        List<ResponseProductImageDto> images = product.getImages().stream().map(
                this::createResponseProductImage
        ).toList();

        return ResponseProductDto.builder()
                .propertyId(product.getPropertyId())
                .qty(product.getQty())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .productImages(images)
                .build();
    }

    private ResponseProductImageDto createResponseProductImage(ProductImage i) {
        return ResponseProductImageDto.builder()
                .hash(dataExtractor.byteArrayToString(i.getHash()))
                .directory(dataExtractor.byteArrayToString(i.getDirectory()))
                .resourceUrl(dataExtractor.byteArrayToString(i.getResourceUrl()))
                .fileName(dataExtractor.byteArrayToString(i.getFileName()))
                .propertyId(i.getPropertyId())
                .build();
    }
}

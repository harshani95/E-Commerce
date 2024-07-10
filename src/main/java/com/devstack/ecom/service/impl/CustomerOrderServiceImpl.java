package com.devstack.ecom.service.impl;

import com.devstack.ecom.dto.request.RequestCustomerOrderDto;
import com.devstack.ecom.dto.response.ResponseCustomerOrderDto;
import com.devstack.ecom.dto.response.paginate.CustomerOrderPaginateDto;
import com.devstack.ecom.entity.Customer;
import com.devstack.ecom.entity.CustomerOrder;
import com.devstack.ecom.entity.Product;
import com.devstack.ecom.exception.EntryNotFoundException;
import com.devstack.ecom.repo.CustomerOrderRepo;
import com.devstack.ecom.repo.CustomerRepo;
import com.devstack.ecom.repo.ProductRepo;
import com.devstack.ecom.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    @Override
    public void create(RequestCustomerOrderDto customerOrderDto) {
        Optional<Customer> selectedCustomer = customerRepo.findById(customerOrderDto.getCustomer());
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }

        Optional<Product> selectedProduct = productRepo.findById(customerOrderDto.getProduct());
        if (selectedProduct.isEmpty()) {
            throw new EntryNotFoundException("Product not found");
        }

        CustomerOrder customerOrder = CustomerOrder.builder()
                .propertyId(UUID.randomUUID().toString())
                .customer(selectedCustomer.get())
                .createdDate(customerOrderDto.getCreatedDate())
                .product(selectedProduct.get())
                .total(customerOrderDto.getTotal())
                .qty(customerOrderDto.getQty())
                .build();
        customerOrderRepo.save(customerOrder);

    }
    @Override
    public ResponseCustomerOrderDto findById(String id) {
        Optional<CustomerOrder> selectedOrder = customerOrderRepo.findById(id);
        if (selectedOrder.isEmpty()) {
            throw new EntryNotFoundException("Order not found");
        }
        return createCustomerOrderDto(selectedOrder.get());
    }

    @Override
    public void update(String id, RequestCustomerOrderDto customerOrderDto) {

    }

    @Override
    public CustomerOrderPaginateDto findAll(String customerId, int page, int size) {
        return CustomerOrderPaginateDto.builder()
                .dataList(customerOrderRepo.findAllWithSearchText(customerId, PageRequest.of(page, size))
                        .stream().map(this::createCustomerOrderDto).toList())
                .count(
                        customerOrderRepo.countAllWithSearchText(customerId)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        customerOrderRepo.deleteById(id);
    }

    private ResponseCustomerOrderDto createCustomerOrderDto(CustomerOrder o){
        return ResponseCustomerOrderDto.builder()
                .propertyId(o.getPropertyId())
                .createdDate(o.getCreatedDate())
                .customer(o.getCustomer().getName())
                .total(o.getTotal())
                .product(o.getProduct().getDescription())
                .qty(o.getQty())
                .build();
    }
}

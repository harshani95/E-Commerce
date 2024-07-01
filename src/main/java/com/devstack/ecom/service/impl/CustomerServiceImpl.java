package com.devstack.ecom.service.impl;

import com.devstack.ecom.dto.request.RequestCustomerDto;
import com.devstack.ecom.dto.response.ResponseCustomerDto;
import com.devstack.ecom.dto.response.paginate.CustomerPaginateDto;
import com.devstack.ecom.entity.Customer;
import com.devstack.ecom.exception.EntryNotFoundException;
import com.devstack.ecom.repo.CustomerRepo;
import com.devstack.ecom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public void create(RequestCustomerDto customerDto) {
        Customer customer = Customer.builder()
                .propertyId(UUID.randomUUID().toString())
                .name(customerDto.getName())
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .isActive(customerDto.isActive())
                .build();
        customerRepo.save(customer);
    }

    @Override
    public ResponseCustomerDto findById(String id) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        return toResponseCustomerDto(selectedCustomer.get());
    }

    @Override
    public void update(String id, RequestCustomerDto customerDto) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        Customer customer = selectedCustomer.get();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setActive(customerDto.isActive());
        customerRepo.save(customer);
    }

    @Override
    public void delete(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public CustomerPaginateDto findAll(String searchText, int page, int size) {
        return CustomerPaginateDto.builder()
                .dataList(customerRepo.findAllWithSearchText(searchText, PageRequest.of(page, size))
                        .stream().map(this::toResponseCustomerDto).toList())
                .count(
                        customerRepo.countAllWithSearchText(searchText)
                )
                .build();
    }

    private ResponseCustomerDto toResponseCustomerDto(Customer customer){
        return ResponseCustomerDto.builder()
                .propertyId(customer.getPropertyId())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .name(customer.getName())
                .isActive(customer.isActive())
                .email(customer.getEmail())
                .build();
    }
}

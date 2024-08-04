package com.devstack.ecom.controller;

import com.devstack.ecom.dto.request.RequestCustomerDto;
import com.devstack.ecom.service.CustomerService;
import com.devstack.ecom.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerDto customerDto) {
        customerService.create(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer created",customerDto.getName()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> update(@PathVariable String id,
                                                   @RequestBody RequestCustomerDto customerDto){
        customerService.update(id,customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer updated!..",null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer data!..",customerService.findById(id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id){
        customerService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Customer deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<StandardResponse>findAll(
         @RequestParam String searchText,
         @RequestParam int page,
         @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer list!..",
                        customerService.findAll(searchText, page, size)),
                HttpStatus.OK
        );
    }


}

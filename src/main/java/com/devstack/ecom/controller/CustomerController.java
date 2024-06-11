package com.devstack.ecom.controller;

import com.devstack.ecom.dto.request.RequestCustomerDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @PostMapping
    public String create(@RequestBody RequestCustomerDto customerDto){
        return "create()";
    }

    @PutMapping
    public String update(@RequestBody RequestCustomerDto customerDto){
        return "update()";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable String id){
        return "getById()";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        return "delete()";
    }

    @GetMapping("/list")
    public String findAll(
         @RequestParam String searchText,
         @RequestParam int page,
         @RequestParam int size
    ){
        return "findAll()";
    }


}

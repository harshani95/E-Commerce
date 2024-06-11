package com.devstack.ecom.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @PostMapping
    public String create(){ return "create()";}

    @PutMapping
    public String update(){ return "update()";}

    @GetMapping("/list")
    public String findAll(){ return "findAll()";}

    @GetMapping
    public String getById(){ return "getById()";}

    @DeleteMapping
    public String delete(){ return "delete()";}
}

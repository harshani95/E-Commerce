package com.devstack.ecom.repo;

import com.devstack.ecom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, String> {
}

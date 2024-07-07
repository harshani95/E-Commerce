package com.devstack.ecom.repo;

import com.devstack.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
}

package com.devstack.ecom.repo;

import com.devstack.ecom.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage,String> {
}

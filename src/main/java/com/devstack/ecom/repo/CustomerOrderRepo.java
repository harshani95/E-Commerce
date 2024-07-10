package com.devstack.ecom.repo;

import com.devstack.ecom.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, String> {
    @Query(value = "SELECT * FROM customer_order WHERE customer_id =?1", nativeQuery = true)
    public Page<CustomerOrder> findAllWithSearchText(String customerId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customer_order WHERE customer_id =?1", nativeQuery = true)
    public long countAllWithSearchText(String customerId);
}

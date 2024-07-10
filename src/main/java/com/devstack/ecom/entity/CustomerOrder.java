package com.devstack.ecom.entity;

import com.devstack.ecom.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customer_order")
public class CustomerOrder {
    @Id
    @Column(name = "property_id", nullable = false, length = 80)
    private String propertyId;
    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false)
    private Date createdDate;
    private double total;
    private int qty;

    @Enumerated(value = EnumType.STRING)
    private PaymentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "property_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "property_id")
    private Product product;
}

package com.devstack.ecom.dto.response;

import com.devstack.ecom.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustomerOrderDto {
    private String propertyId;
    private Date createdDate;
    private double total;
    private int qty;
    private PaymentType type;
    private String customer;
    private String product;
}

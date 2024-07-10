package com.devstack.ecom.dto.request;

import com.devstack.ecom.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCustomerOrderDto {
    private Date createdDate;
    private double total;
    private int qty;
    private PaymentType type;
    private String customer;
    private String product;
}

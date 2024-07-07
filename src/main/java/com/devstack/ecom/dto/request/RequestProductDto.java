package com.devstack.ecom.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProductDto {
    private Long qty;
    private Double unitPrice;
    private String description;
}

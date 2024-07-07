package com.devstack.ecom.dto.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResponseProductDto {
    private String propertyId;
    private Long qty;
    private Double unitPrice;
    private String description;
    List<ResponseProductImageDto> productImages;
}

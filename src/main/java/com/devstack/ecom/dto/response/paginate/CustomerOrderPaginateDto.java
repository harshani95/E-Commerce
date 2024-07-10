package com.devstack.ecom.dto.response.paginate;

import com.devstack.ecom.dto.response.ResponseCustomerOrderDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerOrderPaginateDto {
    private long count;
    private List<ResponseCustomerOrderDto> dataList;
}

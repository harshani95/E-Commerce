package com.devstack.ecom.dto.response.paginate;

import com.devstack.ecom.dto.response.ResponseProductDto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductPaginateDto {
    private long count;
    private List<ResponseProductDto> dataList;
}

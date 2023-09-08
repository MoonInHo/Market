package com.aswemake.market.product.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponseDto {

    private Long id;
    private String productName;
    private Integer price;
}

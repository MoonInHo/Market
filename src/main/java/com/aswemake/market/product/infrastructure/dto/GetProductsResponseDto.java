package com.aswemake.market.product.infrastructure.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductsResponseDto {

    private String ProductName;
    private Integer price;
}

package com.aswemake.market.product.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductsResponseDto {

    private Long id;
    private String productName;
    private Integer price;
}

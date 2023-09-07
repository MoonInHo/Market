package com.aswemake.market.product.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetProductResponseDto {

    private Long productId;
    private String productName;
    private Integer price;
}

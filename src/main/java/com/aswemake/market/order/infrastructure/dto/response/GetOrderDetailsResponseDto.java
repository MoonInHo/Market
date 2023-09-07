package com.aswemake.market.order.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetOrderDetailsResponseDto {

    private String productName;
    private Integer price;
    private Integer quantity;
}

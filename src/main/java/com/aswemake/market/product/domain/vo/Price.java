package com.aswemake.market.product.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Price {

    private final Integer price;
    private final LocalDateTime timestamp;

    private Price(Integer price) {
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public static Price of(Integer price) {

        if (price == null) {
            throw new IllegalArgumentException("상품 가격을 입력하세요.");
        }
        return new Price(price);
    }

    public Integer price() {
        return price;
    }
}

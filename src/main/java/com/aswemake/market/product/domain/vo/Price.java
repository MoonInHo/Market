package com.aswemake.market.product.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Price {

    private final Integer price;
    private final String timestamp;

    private Price(Integer price, String timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public static Price of(Integer price) {

        if (price == null) {
            throw new IllegalArgumentException("상품 가격을 입력하세요.");
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");

        return new Price(price, formatter.format(localDateTime));
    }

    public Integer price() {
        return price;
    }
}

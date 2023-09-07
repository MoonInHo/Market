package com.aswemake.market.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Quantity {

    private final Integer quantity;

    private Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(Integer quantity) {
        return new Quantity(quantity);
    }
}

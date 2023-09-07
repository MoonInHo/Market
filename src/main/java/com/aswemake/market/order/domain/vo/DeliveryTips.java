package com.aswemake.market.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DeliveryTips {

    private final Integer deliveryTips;

    private DeliveryTips(Integer deliveryTips) {
        this.deliveryTips = deliveryTips;
    }

    public static DeliveryTips of(Integer amount) {
        if (amount > 20000) {
            return new DeliveryTips(0);
        }
        return new DeliveryTips(3000);
    }

    public Integer deliveryTips() {
        return deliveryTips;
    }
}

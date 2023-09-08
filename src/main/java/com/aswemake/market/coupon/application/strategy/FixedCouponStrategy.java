package com.aswemake.market.coupon.application.strategy;

import org.springframework.stereotype.Component;

@Component
public class FixedCouponStrategy implements CouponStrategy {

    @Override
    public Double applyDiscount(Double originalPrice, Double discountValue) {
        return originalPrice - discountValue;
    }
}

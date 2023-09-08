package com.aswemake.market.coupon.application.strategy;

import org.springframework.stereotype.Component;

@Component
public class PercentageCouponStrategy implements CouponStrategy {

    @Override
    public Double applyDiscount(Double originalPrice, Double discountValue) {
        return originalPrice * (1.0 - discountValue);
    }
}

package com.aswemake.market.coupon.application.strategy;

public interface CouponStrategy {

    Double applyDiscount(Double originalPrice, Double discountValue);
}

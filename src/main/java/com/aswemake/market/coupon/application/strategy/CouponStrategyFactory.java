package com.aswemake.market.coupon.application.strategy;

import com.aswemake.market.coupon.domain.enums.CouponPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponStrategyFactory {

    private final PercentageCouponStrategy percentageCouponStrategy;
    private final FixedCouponStrategy fixedCouponStrategy;

    public CouponStrategy getStrategy(CouponPolicy couponPolicy) {
        switch (couponPolicy) {
            case PERCENTAGE:
                return percentageCouponStrategy;
            case FIXED:
                return fixedCouponStrategy;
            default:
                throw new IllegalArgumentException("지원하지 않는 쿠폰 입니다.");
        }
    }
}

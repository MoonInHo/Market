package com.aswemake.market.coupon.infrastructure.dto.response;

import com.aswemake.market.coupon.domain.enums.CouponPolicy;
import com.aswemake.market.coupon.domain.enums.CouponScope;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetCouponResponseDto {

    private Long id;
    private CouponPolicy couponPolicy;
    private CouponScope couponScope;
    private Double discountValue;
}

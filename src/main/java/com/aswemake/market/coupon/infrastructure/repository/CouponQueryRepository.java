package com.aswemake.market.coupon.infrastructure.repository;

import com.aswemake.market.coupon.infrastructure.dto.response.GetCouponResponseDto;

import java.util.Optional;

public interface CouponQueryRepository {

    Optional<GetCouponResponseDto> getCoupon(Long couponId);
}

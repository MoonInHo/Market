package com.aswemake.market.coupon.domain.repository;

import com.aswemake.market.coupon.domain.entity.Coupon;
import com.aswemake.market.coupon.infrastructure.repository.CouponQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponQueryRepository {
}

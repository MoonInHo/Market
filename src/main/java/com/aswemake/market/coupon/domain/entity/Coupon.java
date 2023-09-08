package com.aswemake.market.coupon.domain.entity;

import com.aswemake.market.coupon.domain.enums.CouponPolicy;
import com.aswemake.market.coupon.domain.enums.CouponScope;
import com.aswemake.market.coupon.domain.vo.CouponName;
import com.aswemake.market.coupon.domain.vo.Description;
import com.aswemake.market.coupon.domain.vo.DiscountValue;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponPolicy couponPolicy;

    @Embedded
    @Column(nullable = false)
    private CouponName couponName;

    @Embedded
    @Column(nullable = false)
    private Description description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponScope couponScope;

    @Embedded
    private DiscountValue discountValue;
}

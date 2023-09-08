package com.aswemake.market.coupon.infrastructure.repository;

import com.aswemake.market.coupon.infrastructure.dto.response.GetCouponResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.aswemake.market.coupon.domain.entity.QCoupon.coupon;

@Repository
@RequiredArgsConstructor
public class CouponQueryRepositoryImpl implements CouponQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<GetCouponResponseDto> getCoupon(Long couponId) {
        return Optional.ofNullable(queryFactory
                .select(
                        Projections.fields(
                                GetCouponResponseDto.class,
                                Expressions.asNumber(couponId).as("couponId"),
                                coupon.couponPolicy,
                                coupon.couponScope,
                                coupon.discountValue.discountValue
                        )
                )
                .from(coupon)
                .where(coupon.id.eq(couponId))
                .fetchOne());
    }
}

package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.aswemake.market.product.domain.entity.QProductHistory.productHistory;

@Repository
@RequiredArgsConstructor
public class ProductHistoryQueryRepositoryImpl implements ProductHistoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<GetProductResponseDto> getProductHistory(Long productId, String timestamp) {
        return Optional.ofNullable(queryFactory
                .select(
                        Projections.fields(
                                GetProductResponseDto.class,
                                Expressions.asNumber(productId).as("productId"),
                                productHistory.productName.productName,
                                productHistory.price.price
                        )
                )
                .from(productHistory)
                .where(
                        productHistory.product.id.eq(productId),
                        productHistory.price.timestamp.loe(timestamp)
                )
                .orderBy(productHistory.id.desc())
                .fetchFirst()
        );
    }
}

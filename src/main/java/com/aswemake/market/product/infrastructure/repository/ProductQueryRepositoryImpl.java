package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductsResponseDto;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.aswemake.market.product.domain.entity.QProduct.product;
import static com.aswemake.market.product.domain.entity.QProductHistory.productHistory;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isProductNameExist(ProductName productName) {
        return queryFactory
                .selectOne()
                .from(product)
                .where(product.productName.eq(productName))
                .fetchFirst() != null;
    }

    @Override
    public List<GetProductsResponseDto> getProducts() {
        return queryFactory
                .select(
                        Projections.fields(
                                GetProductsResponseDto.class,
                                product.id,
                                product.productName.productName,
                                product.price.price
                        )
                )
                .from(product)
                .fetch();
    }

    @Override
    public Optional<GetProductResponseDto> getProduct(Long productId) {

        return Optional.ofNullable(queryFactory
                .select(
                        Projections.fields(
                                GetProductResponseDto.class,
                                Expressions.asNumber(productId).as("productId"),
                                product.productName.productName,
                                product.price.price
                        )
                )
                .from(product)
                .where(product.id.eq(productId))
                .fetchFirst()
        );
    }
}
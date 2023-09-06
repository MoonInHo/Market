package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.GetProductsResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.aswemake.market.product.domain.entity.QProduct.product;

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
    public List<GetProductsResponseDto> getProduct() {
        return queryFactory
                .select(
                        Projections.fields(
                                GetProductsResponseDto.class,
                                product.productName,
                                product.price.price
                        )
                )
                .from(product) //TODO 카테고리로 분류 기능 추가
                .fetch();
    }
}
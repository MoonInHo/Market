package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.entity.QProduct;
import com.aswemake.market.product.domain.vo.ProductName;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
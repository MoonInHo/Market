package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.entity.QProduct;
import com.aswemake.market.product.domain.vo.Price;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.aswemake.market.product.domain.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateProduct(Long productId, Price price) {
        return queryFactory
                .update(product)
                .set(product.price, price)
                .where(product.id.eq(productId))
                .execute();
    }
}

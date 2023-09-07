package com.aswemake.market.order.infrastructure.repository;

import com.aswemake.market.order.infrastructure.dto.response.GetOrderDetailsResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.aswemake.market.order.domain.entity.QOrderDetail.orderDetail;
import static com.aswemake.market.product.domain.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetOrderDetailsResponseDto> getOrderDetails(Long orderId) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetOrderDetailsResponseDto.class,
                                product.productName.productName,
                                product.price.price,
                                orderDetail.quantity.quantity
                        )
                )
                .from(orderDetail)
                .join(orderDetail.product, product)
                .where(orderDetail.order.id.eq(orderId))
                .fetch();
    }
}

package com.aswemake.market.order.infrastructure.repository;

import com.aswemake.market.order.infrastructure.dto.response.GetOrderDetailsResponseDto;

import java.util.List;

public interface OrderQueryRepository {

    List<GetOrderDetailsResponseDto> getOrderDetails(Long orderId);
}

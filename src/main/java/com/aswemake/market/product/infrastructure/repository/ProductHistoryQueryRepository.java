package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;

import java.util.Optional;

public interface ProductHistoryQueryRepository {

    Optional<GetProductResponseDto> getProductHistory(Long productId, String timestamp);
}

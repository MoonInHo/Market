package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.Price;

public interface ProductCommandRepository {

    Long updateProduct(Long productId, Price price);
}


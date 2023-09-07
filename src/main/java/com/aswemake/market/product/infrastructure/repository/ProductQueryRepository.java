package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductsResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {

    boolean isProductNameExist(ProductName productName);

    List<GetProductsResponseDto> getProducts();

    Optional<GetProductResponseDto> getProduct(Long productId);
}

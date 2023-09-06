package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.GetProductsResponseDto;

import java.util.List;

public interface ProductQueryRepository {

    boolean isProductNameExist(ProductName productName);

    List<GetProductsResponseDto> getProduct();
}

package com.aswemake.market.product.infrastructure.repository;

import com.aswemake.market.product.domain.vo.ProductName;

public interface ProductQueryRepository {

    boolean isProductNameExist(ProductName productName);
}

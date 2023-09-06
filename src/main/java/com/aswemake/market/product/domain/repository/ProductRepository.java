package com.aswemake.market.product.domain.repository;

import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.infrastructure.repository.ProductQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {
}

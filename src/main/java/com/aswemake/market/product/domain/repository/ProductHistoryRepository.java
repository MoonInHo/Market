package com.aswemake.market.product.domain.repository;

import com.aswemake.market.product.domain.entity.ProductHistory;
import com.aswemake.market.product.infrastructure.repository.ProductHistoryQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long>, ProductHistoryQueryRepository {
}

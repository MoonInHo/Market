package com.aswemake.market.order.domain.repository;

import com.aswemake.market.order.domain.entity.Order;
import com.aswemake.market.order.infrastructure.repository.OrderQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {
}

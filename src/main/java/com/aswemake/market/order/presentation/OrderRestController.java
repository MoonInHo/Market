package com.aswemake.market.order.presentation;

import com.aswemake.market.order.application.service.OrderAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderAmountService orderAmountService;

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Integer> getOrderAmount(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderAmountService.getOrderAmount(orderId));
    }
}

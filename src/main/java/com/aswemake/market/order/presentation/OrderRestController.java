package com.aswemake.market.order.presentation;

import com.aswemake.market.order.application.service.OrderAmountService;
import com.aswemake.market.order.infrastructure.dto.request.GetOrderAmountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderAmountService orderAmountService;

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Double> getOrderAmount(
            @PathVariable Long orderId,
            @RequestBody GetOrderAmountRequestDto getOrderAmountRequestDto
    ) {
        return ResponseEntity.ok(orderAmountService.getOrderAmount(orderId, getOrderAmountRequestDto));
    }
}

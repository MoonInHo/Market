package com.aswemake.market.product.application.event;

import com.aswemake.market.product.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final ProductService productService;

    @Async
    @EventListener
    public void onProductHasUpdatedEvent(ProductEvent productEvent) {
        productService.addProductHistory(productEvent.getProductId());
    }
}

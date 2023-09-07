package com.aswemake.market.product.application.event;

public class ProductHasCreatedEvent extends ProductEvent {

    public ProductHasCreatedEvent(Long productId) {
        super(productId);
    }
}

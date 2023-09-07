package com.aswemake.market.product.application.event;

public class ProductHasUpdatedEvent extends ProductEvent {

    public ProductHasUpdatedEvent(Long productId) {
        super(productId);
    }
}

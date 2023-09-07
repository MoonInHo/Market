package com.aswemake.market.product.domain.entity;

import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_history_id")
    private Long id;

    @Embedded
    @Column(nullable = false)
    private ProductName productName;

    @Embedded
    @Column(nullable = false)
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private ProductHistory(ProductName productName, Price price, Product product) {
        this.productName = productName;
        this.price = price;
        this.product = product;
    }

    public static ProductHistory createProductHistory(ProductName productName, Price price, Product product) {
        return new ProductHistory(productName, price, product);
    }
}

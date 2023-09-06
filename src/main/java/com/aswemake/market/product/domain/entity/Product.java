package com.aswemake.market.product.domain.entity;

import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Embedded
    @Column(nullable = false)
    private ProductName productName;

    @Embedded
    @Column(nullable = false)
    private Price price;

    private Product(ProductName productName, Price price) {
        this.productName = productName;
        this.price = price;
    }

    public static Product createProduct(ProductName productName, Price price) {
        return new Product(productName, price);
    }

    public String productName() {
        return productName.productName();
    }
}

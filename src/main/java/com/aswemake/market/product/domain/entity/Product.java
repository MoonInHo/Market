package com.aswemake.market.product.domain.entity;

import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
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

    public void updatePrice(Price price) {
        if (isPriceEquals(price)) {
            throw new IllegalArgumentException("기존 가격으로 변경할 수 없습니다.");
        }
        this.price = price;
    }

    public String productName() {
        return productName.productName();
    }

    private boolean isPriceEquals(Price price) {
        return this.price.price().equals(price.price());
    }
}

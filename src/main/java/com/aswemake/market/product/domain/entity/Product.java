package com.aswemake.market.product.domain.entity;

import com.aswemake.market.order.domain.entity.OrderDetail;
import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private ProductName productName;

    @Embedded
    @Column(nullable = false)
    private Price price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductHistory> productHistories = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    private Product(ProductName productName, Price price) {
        this.productName = productName;
        this.price = price;
    }

    public static Product createProduct(ProductName productName, Price price) {
        return new Product(productName, price);
    }

    public ProductHistory addProductHistory() {
        return ProductHistory.createProductHistory(productName, price, this);
    }

    public Long productId() {
        return id;
    }

    public String productName() {
        return productName.productName();
    }

    public Integer price() {
        return price.price();
    }
}

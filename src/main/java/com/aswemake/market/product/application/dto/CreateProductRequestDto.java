package com.aswemake.market.product.application.dto;

import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {

    private String productName;
    private Integer price;

    public Product toEntity() {
        return Product.createProduct(
                ProductName.of(productName),
                Price.of(price)
        );
    }
}

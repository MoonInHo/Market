package com.aswemake.market.product.application.service;

import com.aswemake.market.exception.exception.DuplicateProductNameException;
import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.repository.ProductRepository;
import com.aswemake.market.product.domain.vo.ProductName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(CreateProductRequestDto createProductRequestDto) {

        checkDuplicateProductName(createProductRequestDto);

        Product product = createProductRequestDto.toEntity();
        productRepository.save(product);
    }

    protected void checkDuplicateProductName(CreateProductRequestDto createProductRequestDto) {

        ProductName productName = ProductName.of(createProductRequestDto.getProductName());

        boolean productNameExist = productRepository.isProductNameExist(productName);
        if (productNameExist) {
            throw new DuplicateProductNameException();
        }
    }
}

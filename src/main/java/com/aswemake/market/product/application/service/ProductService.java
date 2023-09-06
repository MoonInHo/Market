package com.aswemake.market.product.application.service;

import com.aswemake.market.exception.exception.DuplicateProductNameException;
import com.aswemake.market.exception.exception.EmptyProductListException;
import com.aswemake.market.exception.exception.ProductNotFoundException;
import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.repository.ProductRepository;
import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.GetProductsResponseDto;
import com.aswemake.market.product.infrastructure.dto.UpdateProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<GetProductsResponseDto> getProducts() {

        List<GetProductsResponseDto> products = productRepository.getProduct();
        if (products.isEmpty()) {
            throw new EmptyProductListException();
        }
        return products;
    }

    @Transactional
    public void updateProduct(Long productId, UpdateProductRequestDto updateProductRequestDto) {

        Price price = Price.of(updateProductRequestDto.getPrice());

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        product.updatePrice(price);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    protected void checkDuplicateProductName(CreateProductRequestDto createProductRequestDto) {

        ProductName productName = ProductName.of(createProductRequestDto.getProductName());

        boolean productNameExist = productRepository.isProductNameExist(productName);
        if (productNameExist) {
            throw new DuplicateProductNameException();
        }
    }
}

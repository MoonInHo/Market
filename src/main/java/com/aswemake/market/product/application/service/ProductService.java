package com.aswemake.market.product.application.service;

import com.aswemake.market.exception.exception.DuplicateProductNameException;
import com.aswemake.market.exception.exception.EmptyProductListException;
import com.aswemake.market.exception.exception.ProductNotFoundException;
import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.application.event.ProductHasCreatedEvent;
import com.aswemake.market.product.application.event.ProductHasUpdatedEvent;
import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.repository.ProductHistoryRepository;
import com.aswemake.market.product.domain.repository.ProductRepository;
import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.domain.vo.ProductName;
import com.aswemake.market.product.infrastructure.dto.request.GetProductRequestDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductsResponseDto;
import com.aswemake.market.product.infrastructure.dto.request.UpdateProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductHistoryRepository productHistoryRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void createProduct(CreateProductRequestDto createProductRequestDto) {

        checkDuplicateProductName(createProductRequestDto);

        Product product = productRepository.save(createProductRequestDto.toEntity());

        eventPublisher.publishEvent(new ProductHasCreatedEvent(product.productId()));
    }

    @Transactional(readOnly = true)
    public List<GetProductsResponseDto> getProducts() {

        List<GetProductsResponseDto> products = productRepository.getProducts();
        if (products.isEmpty()) {
            throw new EmptyProductListException();
        }
        return products;
    }

    @Transactional(readOnly = true)
    public GetProductResponseDto getProduct(Long productId, GetProductRequestDto getProductRequestDto) {

        String timestamp = getProductRequestDto.timestamp();

        if (isNullAndEmptyTimestamp(timestamp)) {
            return productRepository.getProduct(productId)
                    .orElseThrow(ProductNotFoundException::new);
        }

        return productHistoryRepository.getProductHistory(productId, timestamp)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void updateProduct(Long productId, UpdateProductRequestDto updateProductRequestDto) {

        Price price = Price.of(updateProductRequestDto.price());

        productRepository.updateProduct(productId, price);

        eventPublisher.publishEvent(new ProductHasUpdatedEvent(productId));
    }

    @Transactional
    public void addProductHistory(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        productHistoryRepository.save(product.addProductHistory());
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

    private boolean isNullAndEmptyTimestamp(String timestamp) {
        return timestamp == null || timestamp.isEmpty();
    }
}

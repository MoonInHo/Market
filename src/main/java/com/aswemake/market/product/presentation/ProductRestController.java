package com.aswemake.market.product.presentation;

import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.application.service.ProductService;
import com.aswemake.market.product.infrastructure.dto.GetProductsResponseDto;
import com.aswemake.market.product.infrastructure.dto.UpdateProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('MART')")
    public ResponseEntity<Void> createProduct(
            @RequestBody CreateProductRequestDto createProductRequestDto
    ) {
        productService.createProduct(createProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<GetProductsResponseDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PatchMapping("/{productId}")
    @PreAuthorize("hasRole('MART')")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @RequestBody UpdateProductRequestDto updateProductRequestDto
    ) {
        productService.updateProduct(productId, updateProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('MART')")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId
    ) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}

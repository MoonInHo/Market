package com.aswemake.market.product.application.service;

import com.aswemake.market.exception.exception.DuplicateProductNameException;
import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[상품 서비스] - 유닛 테스트")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("상품명 중복 확인 - 이미 존재하는 상품명으로 중복체크시 예외 발생")
    void existUserId_checkDuplicate_throwException() {
        //given
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto("테스트 상품명", 15000);
        given(productRepository.isProductNameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> productService.checkDuplicateProductName(createProductRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicateProductNameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 상품입니다.");
    }

    @Test
    @DisplayName("상품 생성 - 올바른 상품 정보 입력후 상품 생성 시도시 상품 정보 저장")
    void inputProperProductInfo_createProduct_saveProductInfo() {
        //given
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto("테스트 상품명", 15000);
        Product product = createProductRequestDto.toEntity();

        given(productRepository.save(any())).willReturn(product);

        //when
        productService.createProduct(createProductRequestDto);

        //then
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
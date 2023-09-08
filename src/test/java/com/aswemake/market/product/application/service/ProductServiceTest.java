package com.aswemake.market.product.application.service;

import com.aswemake.market.exception.exception.DuplicateProductNameException;
import com.aswemake.market.exception.exception.EmptyProductListException;
import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import com.aswemake.market.product.application.event.ProductHasCreatedEvent;
import com.aswemake.market.product.application.event.ProductHasUpdatedEvent;
import com.aswemake.market.product.domain.entity.Product;
import com.aswemake.market.product.domain.entity.ProductHistory;
import com.aswemake.market.product.domain.repository.ProductHistoryRepository;
import com.aswemake.market.product.domain.repository.ProductRepository;
import com.aswemake.market.product.domain.vo.Price;
import com.aswemake.market.product.infrastructure.dto.request.GetProductRequestDto;
import com.aswemake.market.product.infrastructure.dto.request.UpdateProductRequestDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductResponseDto;
import com.aswemake.market.product.infrastructure.dto.response.GetProductsResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Optional;

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

    @Mock
    private ProductHistoryRepository productHistoryRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

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
    void inputProperProductInfo_createProduct_saveProductInfo() throws InterruptedException {
        //given
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto("테스트 상품명", 15000);
        Product product = createProductRequestDto.toEntity();

        given(productRepository.save(any())).willReturn(product);
        eventPublisher.publishEvent(new ProductHasCreatedEvent(product.productId()));
        Thread.sleep(1000);

        //when
        productService.createProduct(createProductRequestDto);

        //then
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("상품 목록 조회 - 비어있는 상품 목록 조회시 예외 발생")
    void fetchEmptyProductList_throwException() {
        //given & when
        Throwable throwable = catchThrowable(() -> productService.getProducts());

        //then
        assertThat(throwable).isInstanceOf(EmptyProductListException.class);
        assertThat(throwable).hasMessage("상품 목록이 비어있습니다.");
    }

    @Test
    @DisplayName("상품 목록 조회 - 상품 목록 조회시 조회된 상품 목록 반환")
    void fetchProductList_returnProductList() {
        //given & when
        GetProductsResponseDto getProductsResponseDto = new GetProductsResponseDto(1L, "테스트 상품명", 15000);
        List<GetProductsResponseDto> productList = new ArrayList<>();
        productList.add(getProductsResponseDto);

        given(productRepository.getProducts()).willReturn(productList);

        List<GetProductsResponseDto> products = productService.getProducts();

        //then
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("상품 조회 - 타임스탬프를 입력하지 않고 상품 상세 조회시 조회된 상품 정보 반환")
    void nullAndEmptyTimestamp_fetchProductList_returnProduct(String timestamp) {
        //given
        GetProductRequestDto getProductRequestDto = new GetProductRequestDto(timestamp);
        GetProductResponseDto getProductResponseDto = new GetProductResponseDto(1L, "테스트 상품명", 15000);
        given(productRepository.getProduct(any())).willReturn(Optional.of(getProductResponseDto));

        //when
        GetProductResponseDto product = productService.getProduct(1L, getProductRequestDto);

        //then
        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("상품 조회 - 타임스태프를 입력 받아 상품 조회시 해당 시점의 상품 정보 반환")
    void inputTimestamp_fetchingProductByTimestamp_returnProductInfo() {
        //given
        GetProductRequestDto getProductRequestDto = new GetProductRequestDto("23-09-08 11:00:30");
        GetProductResponseDto getProductResponseDto = new GetProductResponseDto(1L, "테스트 상품명", 15000);
        given(productHistoryRepository.getProductHistory(1L, getProductRequestDto.timestamp()))
                .willReturn(Optional.of(getProductResponseDto));

        //when
        GetProductResponseDto product = productService.getProduct(1L, getProductRequestDto);

        //then
        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("상품 수정 - 적절한 가격을 입력 받아 상품 수정시 변경된 상품정보 저장 후 히스토리 저장")
    void inputProperPrice_updateProduct_saveNewPriceAndSaveProductHistory() throws InterruptedException {
        //given
        UpdateProductRequestDto updateProductRequestDto = new UpdateProductRequestDto(15000);
        given(productRepository.updateProduct(any(), any())).willReturn(1L);

        eventPublisher.publishEvent(new ProductHasUpdatedEvent(1L));
        Thread.sleep(1000);

        //when
        productService.updateProduct(1L, updateProductRequestDto);

        //then
        verify(productRepository, times(1)).updateProduct(any(Long.class), any(Price.class));
    }

    @Test
    @DisplayName("상품 히스토리 추가 - 상품 아이디를 전달받아 상품 조회후 상품 히스토리 저장")
    void inputProductId_fetchProduct_saveProductHistory() {
        //given
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto("테스트 상품명", 15000);
        Product product = createProductRequestDto.toEntity();
        ProductHistory productHistory = product.addProductHistory();
        given(productRepository.findById(any())).willReturn(Optional.of(product));
        given(productHistoryRepository.save(any())).willReturn(productHistory);

        //when
        productService.addProductHistory(1L);

        //then
        verify(productHistoryRepository, times(1)).save(any(ProductHistory.class));
    }

    @Test
    @DisplayName("상품 삭제 - 상품 아이디를 입력 받아 해당 상품 삭제")
    void inputProductId_deleteProduct() {
        //given & when
        productService.deleteProduct(1L);

        //then
        verify(productRepository, times(1)).deleteById(1L);
    }
}
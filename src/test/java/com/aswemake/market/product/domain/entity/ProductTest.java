package com.aswemake.market.product.domain.entity;

import com.aswemake.market.product.application.dto.CreateProductRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[상품 도메인] - 유닛 테스트")
class ProductTest {

    @Test
    @DisplayName("상품 생성 - 올바른 상품 정보를 입력받아 상품 객체 생성시 생성된 상품 객체 반환")
    void inputProperProductInfo_createProduct_returnProductObject() {
        //given
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto("테스트 상품명", 15000);

        //when
        Product product = createProductRequestDto.toEntity();

        //then
        assertThat(product).isNotNull();
        assertThat(product.productName()).isEqualTo("테스트 상품명");
    }
}
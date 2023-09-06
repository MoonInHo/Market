package com.aswemake.market.product.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[상품 이름 객체] - 유닛 테스트")
class ProductNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("상품 이름 입력 - 상품 이름을 입력하지 않을 경우 예외 발생")
    void nullAndEmptyProductName_throwException(String productName) {
        //given & when
        Throwable throwable = catchThrowable(() -> ProductName.of(productName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("상품명을 입력하세요.");
    }
}
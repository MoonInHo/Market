package com.aswemake.market.product.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[상품 가격 객체] - 유닛 테스트")
class PriceTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("가격 입력 - 가격을 입력하지 않을 경우 예외 발생")
    void nullPrice_throwException(Integer price) {
        //given & when
        Throwable throwable = catchThrowable(() -> Price.of(price));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("상품 가격을 입력하세요.");
    }
}
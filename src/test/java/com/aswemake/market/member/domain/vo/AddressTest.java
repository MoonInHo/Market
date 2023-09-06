package com.aswemake.market.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[주소 객체] - 유닛 테스트")
class AddressTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("주소 입력 - 주소를 입력하지 않을 경우 예외 발생")
    void nullAndEmptyAddress_throwException(String address) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of(address, "테스트 상세 주소"));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("주소를 입력하세요.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("주소 입력 - 상세 주소를 입력하지 않을 경우 예외 발생")
    void nullAndEmptyAddressDetail_throwException(String addressDetail) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of("테스트 주소", addressDetail));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("상세 주소를 입력하세요.");
    }

    @Test
    @DisplayName("주소 입력 - 올바른 값 입력시 주소 객체 반환")
    void inputProperAddress_returnAddressObject() {
        //given & when
        Address address = Address.of("테스트 주소", "테스트 상세 주소");

        //then
        assertThat(address).isNotNull();
        assertThat(address.address()).isEqualTo("테스트 주소");
    }
}
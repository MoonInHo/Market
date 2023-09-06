package com.aswemake.market.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[비밀번호 객체] - 유닛 테스트")
class PasswordTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("비밀번호 입력 - 비밀번호를 입력하지 않을 경우 예외 발생")
    void nullAndEmptyPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("비밀번호를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "testPassword 123!", "testPassword123! "})
    @DisplayName("비밀번호 입력 - 비밀번호에 공백 포함시 예외 발생")
    void passwordContainsWhiteSpace_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("비밀번호엔 공백을 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("비밀번호 입력 - 올바른 값 입력시 비밀번호 객체 반환")
    void inputProperPassword_returnPasswordObject() {
        //given & when
        Password password = Password.of("testPassword123!");

        //then
        assertThat(password).isNotNull();
        assertThat(password.password()).isEqualTo("testPassword123!");
    }
}
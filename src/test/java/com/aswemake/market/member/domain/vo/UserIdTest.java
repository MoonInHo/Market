package com.aswemake.market.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유저 아이디 객체] - 유닛 테스트")
class UserIdTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("아이디 입력 - 아이디를 입력하지 않을 경우 예외 발생")
    void nullAndEmptyUserId_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "user 123", "user123 "})
    @DisplayName("아이디 입력 - 아이디에 공백 포함시 예외 발생")
    void userIdContainsWhiteSpace_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디엔 공백을 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("아이디 입력 - 올바른 값 입력시 아이디 객체 반환")
    void inputProperUserId_returnUserIdObject() {
        //given & when
        UserId userId = UserId.of("test123");

        //then
        assertThat(userId).isNotNull();
        assertThat(userId.userId()).isEqualTo("test123");
    }
}
package com.aswemake.market.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[회원 이름 객체] - 유닛 테스트")
class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름 입력 - 이름을 입력하지 않을 경우 예외 발생")
    void nullAndEmptyName_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름을 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "김 개발", "김개발 "})
    @DisplayName("이름 입력 - 이름에 공백 포함시 예외 발생")
    void nameContainsWhiteSpace_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름엔 공백을 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("이름 입력 - 올바른 값 입력시 이름 객체 반환")
    void inputProperName_returnNameObject() {
        //given & when
        Name name = Name.of("김개발");

        //then
        assertThat(name).isNotNull();
        assertThat(name.name()).isEqualTo("김개발");
    }
}
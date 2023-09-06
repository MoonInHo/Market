package com.aswemake.market.member.domain.entity;

import com.aswemake.market.member.application.dto.CreateMemberRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[회원 도메인] - 유닛 테스트")
class MemberTest {

    @Test
    @DisplayName("회원 생성 - 올바른 유저 정보를 입력받아 회원 객체 생성시 생성된 회원 객체 반환")
    void inputProperUserInfo_createMember_returnMemberObject() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "test123",
                "testPassword123!",
                "김개발",
                "테스트 주소",
                "테스트 상세 주소"
        );

        //when
        Member member = createMemberRequestDto.toEntity();

        //then
        assertThat(member).isNotNull();
        assertThat(member.userId()).isEqualTo("test123");
    }
}
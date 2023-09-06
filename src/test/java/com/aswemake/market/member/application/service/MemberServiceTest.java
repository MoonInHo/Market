package com.aswemake.market.member.application.service;

import com.aswemake.market.exception.exception.DuplicateUserIdException;
import com.aswemake.market.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.repository.MemberRepository;
import com.aswemake.market.member.domain.vo.UserId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[회원 서비스] - 유닛 테스트")
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("아이디 중복 확인 - 이미 존재하는 아이디로 중복체크시 예외 발생")
    void existUserId_checkDuplicate_throwException() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "existId123",
                "password",
                "김개발",
                "테스트 주소",
                "테스트 상세 주소"
        );
        given(memberRepository.isUserIdExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.checkDuplicateUserId(createMemberRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicateUserIdException.class);
        assertThat(throwable).hasMessage("이미 존재하는 아이디입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 유저 정보 입력후 회원가입 시도시 회원 생성")
    void inputProperUserInfo_signUp_createMember() {
        //given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "user123",
                "password",
                "김개발",
                "테스트 주소",
                "테스트 상세 주소"
        );
        Member member = createMemberRequestDto.toEntity();
        given(memberRepository.isUserIdExist(any())).willReturn(false);
        given(passwordEncoder.encode(any())).willReturn("encodedPassword");
        given(memberRepository.save(any())).willReturn(member);

        //when
        memberService.signUp(createMemberRequestDto);

        //then
        verify(memberRepository, times(1)).save(any(Member.class));
    }
}
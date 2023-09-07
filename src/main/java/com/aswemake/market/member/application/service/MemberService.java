package com.aswemake.market.member.application.service;

import com.aswemake.market.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.repository.MemberRepository;
import com.aswemake.market.exception.exception.DuplicateUserIdException;
import com.aswemake.market.member.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(CreateMemberRequestDto createMemberRequestDto) {

        checkDuplicateUserId(createMemberRequestDto);

        Member member = createMemberRequestDto.toEntity();
        member.passwordEncrypt(passwordEncoder);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateUserId(CreateMemberRequestDto createMemberRequestDto) {

        UserId userId = UserId.of(createMemberRequestDto.getUserId());

        boolean userIdExist = memberRepository.isUserIdExist(userId);
        if (userIdExist) {
            throw new DuplicateUserIdException();
        }
    }
}

package com.aswemake.market.user.member.application.service;

import com.aswemake.market.user.exception.exception.DuplicateUserIdException;
import com.aswemake.market.user.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.user.member.domain.entity.Member;
import com.aswemake.market.user.member.domain.repository.MemberRepository;
import com.aswemake.market.user.member.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(CreateMemberRequestDto createMemberRequestDto) {

        checkDuplicateUserId(createMemberRequestDto);

        Member member = createMemberRequestDto.toEntity();
        memberRepository.save(member);
    }

    protected void checkDuplicateUserId(CreateMemberRequestDto createMemberRequestDto) {

        UserId userId = UserId.of(createMemberRequestDto.getUserId());

        boolean userIdExist = memberRepository.isUserIdExist(userId);
        if (userIdExist) {
            throw new DuplicateUserIdException();
        }
    }
}

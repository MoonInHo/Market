package com.aswemake.market.member.infrastructure.repository;

import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.vo.UserId;

import java.util.Optional;

public interface MemberQueryRepository {

    Optional<Member> findByUserId(UserId userId);
    boolean isUserIdExist(UserId userId);
}

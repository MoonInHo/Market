package com.aswemake.market.member.infrastructure.repository;

import com.aswemake.market.member.domain.vo.UserId;

public interface MemberQueryRepository {

    boolean isUserIdExist(UserId userId);
}

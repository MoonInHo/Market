package com.aswemake.market.user.member.infrastructure.repository;

import com.aswemake.market.user.member.domain.vo.UserId;

public interface MemberQueryRepository {

    boolean isUserIdExist(UserId userId);
}

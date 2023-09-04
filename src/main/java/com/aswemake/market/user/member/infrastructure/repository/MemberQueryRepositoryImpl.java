package com.aswemake.market.user.member.infrastructure.repository;

import com.aswemake.market.user.member.domain.entity.QMember;
import com.aswemake.market.user.member.domain.vo.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.aswemake.market.user.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean isUserIdExist(UserId userId) {
        return jpaQueryFactory
                .selectOne()
                .from(member)
                .where(member.userId.eq(userId))
                .fetchFirst() != null;
    }
}

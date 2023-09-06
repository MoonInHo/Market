package com.aswemake.market.member.infrastructure.repository;

import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.vo.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.aswemake.market.member.domain.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByUserId(UserId userId) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(member)
                .where(member.userId.eq(userId))
                .fetchOne());
    }

    @Override
    public boolean isUserIdExist(UserId userId) {
        return jpaQueryFactory
                .selectOne()
                .from(member)
                .where(member.userId.eq(userId))
                .fetchFirst() != null;
    }
}

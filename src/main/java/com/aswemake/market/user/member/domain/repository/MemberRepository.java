package com.aswemake.market.user.member.domain.repository;

import com.aswemake.market.user.member.domain.entity.Member;
import com.aswemake.market.user.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}

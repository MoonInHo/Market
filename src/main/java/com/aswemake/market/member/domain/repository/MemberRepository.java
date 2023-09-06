package com.aswemake.market.member.domain.repository;

import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.vo.UserId;
import com.aswemake.market.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}

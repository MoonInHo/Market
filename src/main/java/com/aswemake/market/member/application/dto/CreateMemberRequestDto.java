package com.aswemake.market.member.application.dto;

import com.aswemake.market.member.domain.entity.Member;
import com.aswemake.market.member.domain.vo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto {

    private String userId;
    private String password;
    private String name;
    private String address;
    private String addressDetail;

    public Member toEntity() {
        return Member.createMember(
                UserId.of(userId),
                Password.of(password),
                Name.of(name),
                Address.of(address, addressDetail)
        );
    }
}

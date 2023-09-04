package com.aswemake.market.user.member.application.dto;

import com.aswemake.market.user.member.domain.entity.Member;
import com.aswemake.market.user.member.domain.vo.*;
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
    private String contact;

    public Member toEntity() {
        return Member.createMember(
                UserId.of(userId),
                Password.of(password),
                Name.of(name),
                Address.of(address, addressDetail),
                Contact.of(contact)
        );
    }
}

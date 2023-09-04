package com.aswemake.market.user.member.domain.entity;

import com.aswemake.market.user.member.domain.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @Column(nullable = false)
    private UserId userId;

    @Embedded
    @Column(nullable = false)
    private Password password;

    @Embedded
    @Column(nullable = false)
    private Name name;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Embedded
    @Column(nullable = false)
    private Contact contact;

    private Member(UserId userId, Password password, Name name, Address address, Contact contact) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public static Member createMember(UserId userId, Password password, Name name, Address address, Contact contact) {
        return new Member(userId, password, name, address, contact);
    }
}

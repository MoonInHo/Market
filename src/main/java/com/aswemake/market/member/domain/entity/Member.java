package com.aswemake.market.member.domain.entity;

import com.aswemake.market.member.domain.vo.*;
import com.aswemake.market.order.domain.entity.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    private Member(Long id) {
        this.id = id;
    }

    private Member(UserId userId, Password password, Name name, Address address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = Role.of("ROLE_MART"); //TODO 전달받은 권한에 따라 user / admin 구분하게 변경 & 테스트 후 원복
    }

    public static Member createMember(UserId userId, Password password, Name name, Address address) {
        return new Member(userId, password, name, address);
    }

    public static Member createKey(Long id) {
        return new Member(id);
    }

    public void passwordEncrypt(PasswordEncoder passwordEncoder) {
        this.password = Password.encodedPassword(passwordEncoder.encode(this.password.password()));
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole());
    }

    public String userId() {
        return this.userId.userId();
    }

    public String password() {
        return this.password.password();
    }
}

package com.aswemake.market.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[권한 객체] - 유닛 테스트")
class RoleTest {

    @Test
    @DisplayName("권한 생성 - 권한 정보를 전달받아 권한 객체 생성")
    void _createRoleObject() {
        //given & when
        Role role = Role.of("ROLE_USER");

        //then
        assertThat(role).isNotNull();
        assertThat(role.role()).isEqualTo("ROLE_USER");
    }
}
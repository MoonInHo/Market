package com.aswemake.market.member.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름을 입력하세요.");
        }
        if (name.contains(" ")) {
            throw new IllegalArgumentException("이름엔 공백을 포함할 수 없습니다.");
        }
        return new Name(name);
    }

    public String name() {
        return name;
    }
}

package com.aswemake.market.user.member.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Contact {

    private final String Contact;

    private Contact(String contact) {
        Contact = contact;
    }

    public static Contact of(String contact) {

        if (contact == null || contact.isEmpty()) {
            throw new IllegalArgumentException("연락처를 입력하세요.");
        }
        if (contact.contains(" ")) {
            throw new IllegalArgumentException("연락처엔 공백을 포함할 수 없습니다.");
        }
        return new Contact(contact);
    }
}

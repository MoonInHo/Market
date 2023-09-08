package com.aswemake.market.coupon.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Description {

    private final String description;

    private Description(String description) {
        this.description = description;
    }
}
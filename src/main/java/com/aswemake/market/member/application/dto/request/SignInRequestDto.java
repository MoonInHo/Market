package com.aswemake.market.member.application.dto.request;

public record SignInRequestDto(
        String userId,
        String password
) {
}

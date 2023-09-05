package com.aswemake.market.member.presentation;

import com.aswemake.market.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }
}

package com.aswemake.market.user.member.presentation;

import com.aswemake.market.user.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.user.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }
}

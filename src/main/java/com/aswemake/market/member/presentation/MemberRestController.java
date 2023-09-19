package com.aswemake.market.member.presentation;

import com.aswemake.market.member.application.dto.CreateMemberRequestDto;
import com.aswemake.market.member.application.dto.request.SignInRequestDto;
import com.aswemake.market.member.application.security.JwtUtil;
import com.aswemake.market.member.application.service.MemberService;
import com.aswemake.market.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    public ResponseEntity<Void> testPage() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.userId(),
                        signInRequestDto.password()
                )
        );

        Member principal = (Member) authenticate.getPrincipal();

        return ResponseEntity.ok().body(jwtUtil.generateToken(principal.userId()));
    }
}

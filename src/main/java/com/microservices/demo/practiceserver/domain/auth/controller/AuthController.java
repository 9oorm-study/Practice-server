package com.microservices.demo.practiceserver.domain.auth.controller;

import com.microservices.demo.practiceserver.domain.auth.service.AuthService;
import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberRequestDTO.SignupRequest request) {
        authService.signup(request);
        return "성공";
    }

    @PostMapping("/login")
    public MemberResponseDTO.MemberLoginResponse login(@RequestBody MemberRequestDTO.LoginRequest request) {
        return authService.login(request);
    }
}

package com.microservices.demo.practiceserver.domain.auth.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;

public interface AuthService {
    void signup(MemberRequestDTO.SignupRequest request);
    MemberResponseDTO.MemberLoginResponse login(MemberRequestDTO.LoginRequest request);
}

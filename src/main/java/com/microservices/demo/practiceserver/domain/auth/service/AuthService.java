package com.microservices.demo.practiceserver.domain.auth.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;

public interface AuthService {
    void signup(MemberRequestDTO.SignupRequest request);
}

package com.microservices.demo.practiceserver.domain.auth.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(MemberRequestDTO.SignupRequest request) {
        memberRepository.save(
                Member.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .email("")
                        .nickname("")
                        .build()
        );
    }
}

package com.microservices.demo.practiceserver.domain.auth.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import com.microservices.demo.practiceserver.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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

    @Override
    public MemberResponseDTO.MemberLoginResponse login(MemberRequestDTO.LoginRequest request) {
        String username = request.getUsername();
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            return MemberResponseDTO.MemberLoginResponse.toMemberLoginResponse(jwtProvider.createAccessToken(member), jwtProvider.createRefreshToken(member));
        }
        throw new BadCredentialsException(member.getUsername());
    }
}

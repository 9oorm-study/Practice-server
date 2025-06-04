package com.microservices.demo.practiceserver.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) {
        // 1. 헤더에서 토큰 가져오기
        String authorizationHeader = request.getHeader("Authorization");

        // 2. 토큰 추출하기
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (JwtProvider.isTokenValid(token)) {
                String email = JwtProvider.getUsername(token);
            }
        }

        // 3. 토큰 검증하기

        // 4. 정보 securityContextHolder에 넣기
    }
}

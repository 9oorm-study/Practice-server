package com.microservices.demo.practiceserver.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.demo.practiceserver.global.service.CustomUserDetailService;
import com.microservices.demo.practiceserver.global.util.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 토큰을 뽑기
        // 토큰이 유효한지 확인
        // 토큰에서 사용자 정보(username)를 뽑기

        // 인증 객체 만들고 SecurityContextHolder에 인증 정보를 넣어주기

         // 에러 처리하기

        try {
            String header = request.getHeader(AUTHORIZATION_HEADER);
            if (header != null && header.startsWith(TOKEN_PREFIX)) {
                String token = header.substring(TOKEN_PREFIX.length());
                try {
                    String username = jwtProvider.getUsername(token);
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    throw e;
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.setStatus(401);
            ObjectMapper ob = new ObjectMapper();
            ob.writeValue(response.getOutputStream(), "토큰의 유효기간이 만료되었습니다.");
        } catch (Exception e) {
            response.setStatus(401);
            ObjectMapper ob = new ObjectMapper();
            ob.writeValue(response.getOutputStream(), "인증 실패");
        }

    }
}

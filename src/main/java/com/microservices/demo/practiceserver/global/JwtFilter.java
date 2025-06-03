package com.microservices.demo.practiceserver.global;

import com.microservices.demo.practiceserver.global.service.CustomUserDetailService;
import com.microservices.demo.practiceserver.global.util.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Repository
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "authorization";
    private static final String TOKEN_PREFIX="Bearer";
    private JwtProvider jwtProvider;
    private CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        // 헤더에서 토큰 뽑기
        String header = request.getHeader(AUTHORIZATION_HEADER);

        // 토큰이 유효한지 화인
        if(header!=null && header.startsWith(TOKEN_PREFIX)){
            String token=header.substring(TOKEN_PREFIX.length());
            // 토큰에서 사용자 정보(username)을 뽑기
            try{
                String username=jwtProvider.getUsername(token);
                UserDetails userDetails= customUserDetailService.loadUserByUsername(username);
                // 인증 객체 만들기
                // Authentication 를 직접 만들어줘야 함.
                Authentication jwtAuthenticationToken=new JwtAuthenticationToken(token);
                Authentication authentication= jwtProvider.authenticate(jwtAuthenticationToken);
                // 인증 객체 만들고 SecruityContextHolder에 인증정보를 넣어주기
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                // 부가 내용 : 에러 처리 하기
            }
        }



        filterChain.doFilter(reqesst, response);

    }
}

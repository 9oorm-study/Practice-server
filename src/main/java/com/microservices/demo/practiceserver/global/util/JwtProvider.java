package com.microservices.demo.practiceserver.global.util;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.time.Instant;
@Component
public class JwtProvider {
    private final String secretKey;
    private final Long tokenValidityInMilliseconds;
    private long accessExpiration;
    private long refreshExpiration;
    private MemberRepository memberRepository;


    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.token-validity-in-seconds}") Long tokenValidityInSeconds) {
        this.secretKey = secretKey;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds;
    }


    public String createToken(Authentication authentication){
        return Jwts.builder()
                .setHeader
                .setSubject
                .claim
                .setIssuedAt
                .setExpriation
                .signWith
                .compact();
    }



    public Jws<Claims> getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new RuntimeException("토큰 파싱 실패", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            return true;
        } catch (JwtException e) {
            log.error("JWT 오류: " + e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("토큰 유효성 검사 실패: " + e.getMessage());
            return false;
        }
    }




    public String getUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username));
        return member.getUsername();
    }

}

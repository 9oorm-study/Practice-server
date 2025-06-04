package com.microservices.demo.practiceserver.global;

import com.microservices.demo.practiceserver.domain.auth.dto.JwtTokenDTO;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

// 토큰을 만드는 곳
@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final Long accessExpMs;
    private final MemberRepository memberRepository;


    public JwtProvider(
            @Value("${spring.jwt.secret}") String secretKey,
            @Value("${spring.jwt.access-token-expiration") Long access) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpMs = access;
    }

    public String createAccessToken(String username, String role) {
        return createToken(username, role, accessExpMs);
    }

    private String createToken(String username, String role, long validityMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("username", username);
        claims.put("role", role);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getBody().get("username", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date expiredDate = claims.getBody().getExpiration();
            Date now = new Date();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_EXPIRE_TOKEN);
        } catch (SignatureException
                 | SecurityException
                 | IllegalArgumentException
                 | MalformedJwtException
                 | UnsupportedJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_INVALID_TOKEN);
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }
}

package com.microservices.demo.practiceserver.global.domain;


import com.microservices.demo.practiceserver.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Member member;
    private String AUTHORITY;

    public CustomUserDetails(Member member){
        this.member=member;
    }

    @Override
    public String getUsername(){
        return member.getUsername();
    }

    @Override
    public String getPassword(){
        return member.getPassword();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority>getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

}

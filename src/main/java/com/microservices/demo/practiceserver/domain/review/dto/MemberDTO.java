package com.microservices.demo.practiceserver.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDTO{
    private final Long member_id;
    private final String email;
    private final String nickname;
    private final String password;

    public MemberDTO(Long member_id, String email, String nickname, String password) {
        this.member_id = member_id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
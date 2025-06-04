package com.microservices.demo.practiceserver.domain.member.dto.request;

import lombok.Getter;

import java.time.LocalDate;

public class MemberRequestDTO {

    @Getter
    public static class PostMemberInfoRequest {
        private String username;
        private String password;
        private String email;
        private String nickname;
    }

    @Getter
    public static class UpdateMemberInfoRequest {
        private Long memberId;
        private String nickname;
        private Integer height;
        private Integer weight;
        private String introduce;
        private String phone;
        private LocalDate birth;
    }

    @Getter
    public static class SignupRequest {
        private String username;
        private String password;
    }

}

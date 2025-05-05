package com.microservices.demo.practiceserver.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class postMemberInfoResponse {
        private Long memberId;
        private String username;
        private String password;
        private String email;
        private String nickname;
    }

    @Getter
    @Builder
    public static class getMmeberInfoResponse {
        private Long memberId;
        private String username;
        private String password;
        private String email;
        private String nickname;
    }

    @Getter
    @Builder
    public static class updateMemberInfoResponse {
        private Long memberId;
        private String nickname;
        private Integer height;
        private Integer weight;
        private String introduce;
        private String phone;
        private LocalDate birth;
    }

}

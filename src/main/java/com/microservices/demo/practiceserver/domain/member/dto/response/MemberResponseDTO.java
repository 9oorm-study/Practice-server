package com.microservices.demo.practiceserver.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class createMemberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;
    }

    @Getter
    @Builder
    public static class memberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;
        
    }

    @Getter
    @Builder
    public static class memberInfoListResponse {
        private List<memberInfoResponse> members;
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

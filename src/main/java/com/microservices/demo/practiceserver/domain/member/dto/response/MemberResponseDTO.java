package com.microservices.demo.practiceserver.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class CreateMemberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;
    }

    @Getter
    @Builder
    public static class MemberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;
        
    }

    @Getter
    @Builder
    public static class MemberInfoListResponse {
        private Integer currentPageNum;
        private Integer totalPageNum;
        private List<MemberInfoResponse> members;
    }

    @Getter
    @Builder
    public static class UpdateMemberInfoResponse {
        private String nickname;
        private Integer height;
        private Integer weight;
        private String introduce;
        private String phone;
        private LocalDate birth;
    }

}

package com.microservices.demo.practiceserver.domain.member.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MemberRequestDTO {

    @Getter
    public static class postMemberInfoRequest {
        private String username;
        private String password;
        private String email;
        private String nickname;
    }

    @Getter
    public static class updateMemberInfoRequest {
        private String nickname;
        private Integer height;
        private Integer weight;
        private String introduce;
        private String phone;
        private LocalDate birth;
    }


}

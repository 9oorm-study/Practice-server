package com.microservices.demo.practiceserver.domain.auth.dto;

import lombok.Getter;

public class AuthRequestDTO {

    @Getter
    public class LoginRequestDTO {
        private String username;
        private String password;
    }
}

package com.microservices.demo.practiceserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private int height;
    private int weight;
    private String introduce;
    private String phone;
    private LocalDate birth;

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<MemberProduct> memberProducts;

    @OneToOne(fetch = FetchType.LAZY)
    private Cart cart;


}

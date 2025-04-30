package com.microservices.demo.practiceserver.domain.member.entity;

import com.microservices.demo.practiceserver.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "introduce")
    @Lob
    private String introduce;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth")
    private LocalDate birth;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}

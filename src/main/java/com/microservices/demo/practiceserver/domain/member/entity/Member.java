package com.microservices.demo.practiceserver.domain.member.entity;

import com.microservices.demo.practiceserver.domain.cart.entity.Cart;
import com.microservices.demo.practiceserver.domain.product.entity.MemberProduct;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

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
    private String introduce;

    @Column(name = "phoneNum")
    private String phone;

    @Column(name = "birth")
    private LocalDate birth;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberProduct> memberProductList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Cart> cartList;
}

package com.microservices.demo.practiceserver.domain.product.entity.mapping;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.enums.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member_product")
public class MemberProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_product_id")
    private Long id;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

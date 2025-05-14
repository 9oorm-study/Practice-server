package com.microservices.demo.practiceserver.domain.product.dto;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import com.microservices.demo.practiceserver.domain.product.entity.Cart;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.repository.CartRepository;
import com.microservices.demo.practiceserver.domain.product.repository.ProductRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

public class CartRequestDTO {
    @Getter
    public static class CreateCartDTO{
        private Long memberId;
        private Long productId;


    }

    @Getter
    public static class ReadCartDTO{
        private Long memberId;
        private Long productId;
    }

}

package com.microservices.demo.practiceserver.domain.review.dto;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import lombok.*;


public class ReviewRequestDTO {




    @Getter
    public static class CreateReviewDTO{

        private Double score;
        private String content;
        private Long memberId;
        private Long memberProductId;



    }
    @Getter
    public static class ReadReviewDTO{
        private Double score;
        private String content;
        private Member member;
        private MemberProduct memberProduct;
    }
}

package com.microservices.demo.practiceserver.domain.review.dto;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReviewResponseDTO {




    @Builder
    @AllArgsConstructor
    @Getter
    public static class CreateReviewResponseDTO{
        private final Long id;
        private final Double score;
        private final String content;
        private final MemberDTO member;
        private final MemberProductDTO memberProduct;
    }




    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReviewListResponseDTO{
        private final Long id;
        private final Double score;
        private final String content;
        private final MemberDTO member;
        private final MemberProductDTO memberProduct;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReviewDetailResponseDTO{
        private final Long id;
        private final Double score;
        private final String content;
        private final MemberDTO member;
        private final MemberProductDTO memberProduct;

    }
}

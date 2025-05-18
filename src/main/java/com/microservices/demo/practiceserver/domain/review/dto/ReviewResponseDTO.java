package com.microservices.demo.practiceserver.domain.review.dto;

import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.dto.ProductResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ReviewResponseDTO {




    @Builder
    @AllArgsConstructor
    @Getter
    public static class CreateReviewResponseDTO{
        private final Long id;
        private final Double score;
        private final String content;
        private final MemberResponseDTO.MemberInfoResponse member;
        private final ProductResponseDTO.ProductDetailResponseDTO memberProduct;
    }




    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReviewListResponseDTO{

        private List<ReviewDetailResponseDTO>items;
        private Long cursor;
        private boolean hasNext;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReviewDetailResponseDTO{
        private final Long id;
        private final Double score;
        private final String content;
        private final MemberResponseDTO.MemberInfoResponse member;
        private final ProductResponseDTO.ProductDetailResponseDTO memberProduct;

    }

    @Getter
    @Builder
    public static class ReviewUpdateResponseDTO{
        private Long reviewId;
        private Double score;
        private String content;
        public static ReviewUpdateResponseDTO toReviewUpdateResponseDTO(Review review){
            return ReviewUpdateResponseDTO.builder()
                    .reviewId(review.getId())
                    .score(review.getScore())
                    .content(review.getContent())
                    .build();
        }
    }
}
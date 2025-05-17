package com.microservices.demo.practiceserver.domain.review.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.dto.ProductResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.review.dto.ReviewRequestDTO;
import com.microservices.demo.practiceserver.domain.review.dto.ReviewResponseDTO;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import com.microservices.demo.practiceserver.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO.MemberInfoResponse;


@RestController
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDTO.CreateReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO.CreateReviewDTO request) {

        Review review = reviewService.createReview(request);
        ReviewResponseDTO.CreateReviewResponseDTO response = ReviewResponseDTO.CreateReviewResponseDTO.builder()
                .id(review.getId())
                .score(review.getScore())
                .content(review.getContent())
                .member(toMemberInfoResponse(review.getMember()))
                .memberProduct(toProductDetailResponseDTO(review.getMemberProduct().getProduct()))

                .build();

        return ResponseEntity.ok(response);
    }
    private MemberInfoResponse toMemberInfoResponse(Member member){
        return MemberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
    private ProductResponseDTO.ProductDetailResponseDTO toProductDetailResponseDTO(Product product){
        return ProductResponseDTO.ProductDetailResponseDTO.toProductDetailResponseDTO(product);
    }
    @GetMapping("/reviewLists")
    public List<ReviewResponseDTO.ReviewListResponseDTO> getReviewList() {
        List<Review> reviewList = reviewService.getReviewList();

        List<ReviewResponseDTO.ReviewListResponseDTO> dtos = reviewList.stream()
                .map(review -> new ReviewResponseDTO.ReviewListResponseDTO(review.getId(),
                        review.getScore(),
                        review.getContent(),
                        toMemberInfoResponse(review.getMember()),
                        toProductDetailResponseDTO(review.getMemberProduct().getProduct())
                        )).collect(Collectors.toList());

        return dtos;
    }

    @JsonIgnoreProperties
    @GetMapping("/reviews/{reviewID}")
    public ReviewResponseDTO.ReviewDetailResponseDTO getReview(@PathVariable("reviewID") Long reviewID) {
        Review review = reviewService.getReview(reviewID);
        ReviewResponseDTO.ReviewDetailResponseDTO response = ReviewResponseDTO.ReviewDetailResponseDTO.builder()
                .id(review.getId())
                .score(review.getScore())
                .member(toMemberInfoResponse(review.getMember()))
                .memberProduct(toProductDetailResponseDTO(review.getMemberProduct().getProduct()))
                .build();

        return response;
    }

    @PatchMapping("/{reviewId}")
    public ReviewResponseDTO.ReviewUpdateResponseDTO updateReview(@PathVariable("reviewId") Long reviewId,@RequestBody ReviewRequestDTO.UpdateReviewDTO request){
        Review review = reviewService.updateReview(reviewId,request);
        return ReviewResponseDTO.ReviewUpdateResponseDTO.toReviewUpdateResponseDTO(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}

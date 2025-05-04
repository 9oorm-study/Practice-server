package com.microservices.demo.practiceserver.domain.review.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.review.dto.MemberDTO;
import com.microservices.demo.practiceserver.domain.review.dto.MemberProductDTO;
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
                .member(toMemberDTO(review.getMember()))
                .memberProduct(toMemberProductDTO(review.getMemberProduct()))
                .build();

        return ResponseEntity.ok(response);
    }
    public MemberDTO toMemberDTO(Member member){
        return new MemberDTO(member.getId(), member.getEmail(), member.getNickname(), member.getPassword());
    }
    public MemberProductDTO toMemberProductDTO(MemberProduct memberProduct){
        return new MemberProductDTO(memberProduct.getId(), memberProduct.getId(), memberProduct.getId(), memberProduct.getSize());
    }
    @GetMapping("/reviewList")
    public List<ReviewResponseDTO.ReviewListResponseDTO> getReviewList() {
        List<Review> reviewList = reviewService.getReviewList();

        List<ReviewResponseDTO.ReviewListResponseDTO> dtos = reviewList.stream()
                .map(review -> new ReviewResponseDTO.ReviewListResponseDTO(review.getId(),
                        review.getScore(),
                        review.getContent(),
                        toMemberDTO(review.getMember()),
                        toMemberProductDTO(review.getMemberProduct())
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
                .member(toMemberDTO(review.getMember()))
                .memberProduct(toMemberProductDTO(review.getMemberProduct()))
                .build();

        return response;
    }


}

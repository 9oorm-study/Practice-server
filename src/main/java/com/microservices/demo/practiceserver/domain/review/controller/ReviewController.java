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
import org.springframework.data.domain.Slice;
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
    @GetMapping("/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponseDTO>getReviews(
            @RequestParam(value = "cursor",required = false,defaultValue = "0")Long cursor,
            @RequestParam(value = "size",required = false,defaultValue = "10")Integer size
    ){
        //cursor와 size를 service 에 넘겨서 로직 처리
        Slice<Review> reviews = reviewService.getReviews(cursor,size);

        //Slice<Review>내부에 List<Review>가 있는데, 우리가 실제로 보여줄 리뷰 리스트만 꺼낸다.
        List<Review>reviewList=reviews.getContent();

        //Review 객체들을 보내기 편한 DTO로 변환
        List<ReviewResponseDTO.ReviewDetailResponseDTO>items=reviewList.stream()
                .map(review -> new ReviewResponseDTO.ReviewDetailResponseDTO(
                        review.getId(),
                        review.getScore(),
                        review.getContent(),
                        toMemberInfoResponse(review.getMember()),
                        toProductDetailResponseDTO(review.getMemberProduct().getProduct())
                ))
                .collect(Collectors.toList());
        //응답 DTO를 이렇게 구성
        ReviewResponseDTO.ReviewListResponseDTO response= ReviewResponseDTO.ReviewListResponseDTO.builder()
                .items(items)
                .cursor(reviews.hasNext()?reviewList.get(reviewList.size()-1).getId():null)
                .hasNext(reviews.hasNext())
                .build();
        return ResponseEntity.ok(response);
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

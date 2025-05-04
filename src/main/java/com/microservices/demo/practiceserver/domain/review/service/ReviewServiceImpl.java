package com.microservices.demo.practiceserver.domain.review.service;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.review.dto.MemberDTO;
import com.microservices.demo.practiceserver.domain.review.dto.ReviewRequestDTO;
import com.microservices.demo.practiceserver.domain.review.dto.ReviewResponseDTO;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import com.microservices.demo.practiceserver.domain.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request){


        Review review = Review.builder()
                .score(request.getScore())
                .content(request.getContent())
                .member(request.getMember())
                .memberProduct(request.getMemberProduct())
                .build();


        reviewRepository.save(review);
        return null;
    }


    @Override
    public List<Review> getReviewList(){
        return reviewRepository.findAll();

    }

    @Override
    public Review getReview(Long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(()->new RuntimeException("리뷰를 찾지 못했습니다"));
    }
}

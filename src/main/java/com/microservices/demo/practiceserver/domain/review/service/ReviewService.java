package com.microservices.demo.practiceserver.domain.review.service;

import com.microservices.demo.practiceserver.domain.review.dto.ReviewRequestDTO;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    Review createReview(ReviewRequestDTO.CreateReviewDTO request);

    List<Review> getReviewList();

    Review getReview(Long reviewId);

    Review updateReview(Long reviewId, ReviewRequestDTO.UpdateReviewDTO request);

    void deleteReview(Long reviewId);
}

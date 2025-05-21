package com.microservices.demo.practiceserver.domain.review.service;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.product.repository.MemberProductRepository;
import com.microservices.demo.practiceserver.domain.review.dto.ReviewRequestDTO;
import com.microservices.demo.practiceserver.domain.review.entity.Review;
import com.microservices.demo.practiceserver.domain.review.repository.ReviewRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;
    private final MemberProductRepository memberProductRepository;

    @Override
    public Review createReview(ReviewRequestDTO.CreateReviewDTO request){
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("존재 하지 않는 회원입니다"));
        MemberProduct memberProduct = memberProductRepository.findById(request.getMemberProductId())
                .orElseThrow(()->new IllegalArgumentException("존재 하지 않는 멤버-상품입니다"));

        Review review = Review.builder()
                .score(request.getScore())
                .content(request.getContent())
                .member(member)
                .memberProduct(memberProduct)
                .build();



        return reviewRepository.save(review);
    }


    @Override
    public Slice<Review> getReviews(Long cursor, Integer size){
        Pageable pageable= PageRequest.of(0,size);
        if(cursor==0L){
            return reviewRepository.findAllByOrderByIdAsc(pageable);
        }
        return reviewRepository.findAllByIdGreaterThanOrderByIdAsc(cursor,pageable);
    }

    @Override
    public Review getReview(Long reviewId){
        return findReview(reviewId);
    }


    @Override
    @Transactional
    public Review updateReview(Long reviewId, ReviewRequestDTO.UpdateReviewDTO request){
        Review review= findReview(reviewId);
        review.updateReview(request.getScore(),request.getContent());
        return review;
    }

    @Override
    public void deleteReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }

    private Review findReview(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(()->new RuntimeException("리뷰를 찾지 못했습니다"));
    }

}

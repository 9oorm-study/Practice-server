package com.microservices.demo.practiceserver.domain.review.repository;

import com.microservices.demo.practiceserver.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


}

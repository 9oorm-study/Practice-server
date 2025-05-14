package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberProductRepository extends JpaRepository<MemberProduct, Long> {

    List<MemberProduct> findByMemberId(Long memberId);
    List<MemberProduct> findByProductId(Long productId);
}

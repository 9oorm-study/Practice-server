package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.Product;

import java.util.List;

public interface MemberProductService {

    public List<Product> getProductsByMemberId(Long memberId);
    public List<Member> getMembersByProductId(Long productId);

}

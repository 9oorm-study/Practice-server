package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.mapping.MemberProduct;
import com.microservices.demo.practiceserver.domain.product.repository.MemberProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberPrdouctServiceImpl implements MemberProductService {

    private final MemberProductRepository memberProductRepository;

    @Override
    public List<Product> getProductsByMemberId(Long memberId) {
        List<MemberProduct> memberProducts = memberProductRepository.findByMemberId(memberId);
        return memberProducts.stream() 
                .map(MemberProduct::getProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getMembersByProductId(Long productId) {
        List<MemberProduct> memberProducts = memberProductRepository.findByProductId(productId);
        return memberProducts.stream()
                .map(MemberProduct::getMember)
                .collect(Collectors.toList());
    }
}

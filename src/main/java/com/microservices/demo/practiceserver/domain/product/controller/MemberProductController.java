package com.microservices.demo.practiceserver.domain.product.controller;

import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.dto.ProductResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.service.MemberProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberProductController {

    private final MemberProductService memberProductService;

    @GetMapping("/member-product/{memberId}")
    public ProductResponseDTO.ProductListResponseDTO getProductsByMemberId(@PathVariable Long memberId) {
        List<Product> products = memberProductService.getProductsByMemberId(memberId);

        return ProductResponseDTO.ProductListResponseDTO.toProductListResponseDTO(products);
    }

    @GetMapping("/member-product/{productId}")
    public MemberResponseDTO.memberInfoListResponse getMembersByProductId(@PathVariable Long productId) {
        List<Member> members = memberProductService.getMembersByProductId(productId);

        return MemberResponseDTO.memberInfoListResponse.builder()
                .members(members.stream()
                        .map(member -> MemberResponseDTO.memberInfoResponse.builder()
                                .memberId(member.getId())
                                .username(member.getUsername())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .build())
                        .toList())
                .build();
    }

}

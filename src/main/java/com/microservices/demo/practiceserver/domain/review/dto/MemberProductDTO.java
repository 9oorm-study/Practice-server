package com.microservices.demo.practiceserver.domain.review.dto;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
@Data
@AllArgsConstructor
@Builder
public class MemberProductDTO{
    private final Long memberId;
    private final Long memberProductId;
    private final Long productId;
    private final Size size;





}
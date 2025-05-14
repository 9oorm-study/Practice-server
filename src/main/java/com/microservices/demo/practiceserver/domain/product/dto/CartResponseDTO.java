package com.microservices.demo.practiceserver.domain.product.dto;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.product.entity.Cart;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CartResponseDTO {
    @Getter
    @Builder
    public static class CreateCartResponseDTO{
        private Long cartId;
        private Long memberId;
        private Long productId;

        public static CreateCartResponseDTO toCreateCartResponseDTO(Cart cart){
            return CreateCartResponseDTO.builder()
                    .cartId(cart.getId())
                    .memberId(cart.getMember().getId())
                    .productId(cart.getProduct().getId())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class CartListResponseDTO{
        private List<CartItemResponseDTO> list;

        public static CartListResponseDTO toCartListResponseDTO(List<Cart>carts){
            return CartListResponseDTO.builder()
                    .list(carts.stream().map(CartItemResponseDTO::toCartItemResponseDTO).toList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class CartItemResponseDTO{
        private Long cartId;
        private Long memberId;
        private Long productId;

        public static CartItemResponseDTO toCartItemResponseDTO(Cart cart){
            return CartItemResponseDTO.builder()
                    .cartId(cart.getId())
                    .memberId(cart.getMember().getId())
                    .productId(cart.getProduct().getId())
                    .build();
        }
    }
}

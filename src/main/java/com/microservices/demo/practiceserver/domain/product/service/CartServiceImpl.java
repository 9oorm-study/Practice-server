package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import com.microservices.demo.practiceserver.domain.product.dto.CartRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Cart;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.repository.CartRepository;
import com.microservices.demo.practiceserver.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    @Override
    public Cart createCart(CartRequestDTO.CreateCartDTO request){

        Member member =memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new RuntimeException("회원을 찾지 못했습니다"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()->new RuntimeException("상품을 찾지 못했습니다"));

        Cart cart=Cart.builder()
                .member(member)
                .product(product)
                .build();

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCarts(){
        return cartRepository.findAll();
    }

    @Override
    public Cart getCart(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(
                ()->new RuntimeException("장바구니를 찾지 못했습니다.")
        );
    }
}

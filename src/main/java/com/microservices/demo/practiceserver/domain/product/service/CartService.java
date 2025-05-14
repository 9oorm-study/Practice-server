package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.CartRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Cart;

import java.util.List;

public interface CartService {
    Cart createCart(CartRequestDTO.CreateCartDTO request);

    List<Cart> getCarts();

    Cart getCart(Long cartId);
}

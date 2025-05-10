package com.microservices.demo.practiceserver.domain.product.controller;

import com.microservices.demo.practiceserver.domain.product.dto.CartRequestDTO;
import com.microservices.demo.practiceserver.domain.product.dto.CartResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Cart;
import com.microservices.demo.practiceserver.domain.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/carts")
    public ResponseEntity<CartResponseDTO.CreateCartResponseDTO> createCart(@RequestBody CartRequestDTO.CreateCartDTO reqeust){
        Cart cart = cartService.createCart(reqeust);
        return ResponseEntity.status(HttpStatus.CREATED).body(CartResponseDTO.CreateCartResponseDTO.toCreateCartResponseDTO(cart));
            }

    @GetMapping("/carts")
    public CartResponseDTO.CartListResponseDTO getCarts(){
        List<Cart> carts = cartService.getCarts();
        return CartResponseDTO.CartListResponseDTO.toCartListResponseDTO(carts);
    }

    @GetMapping("/carts/{cartId}")
    public CartResponseDTO.CartItemResponseDTO getCart(@PathVariable("cartId") Long cartId){
        Cart cart = cartService.getCart(cartId);
        return CartResponseDTO.CartItemResponseDTO.toCartItemResponseDTO(cart);

    }
}

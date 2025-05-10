package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}

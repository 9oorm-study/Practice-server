package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

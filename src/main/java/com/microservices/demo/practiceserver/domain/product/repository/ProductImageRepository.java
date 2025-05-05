package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}

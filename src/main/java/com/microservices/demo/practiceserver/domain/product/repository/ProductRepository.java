package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPA Query method
    List<Product> findAllByDeletedAtIsNull();
    Page<Product> findAllByDeletedAtIsNull(Pageable pageable);

    // JPQL
    @Query("SELECT p FROM Product p WHERE p.deletedAt = null")
    Page<Product> findAllByDeletedAtIsNullWithJPQL(Pageable pageable);

    // native query
    @Query(value = "SELECT p.* FROM Product p WHERE p.deleted_at = null", nativeQuery = true)
    List<Product> findAllByDeletedAtIsNullWithNative();
}

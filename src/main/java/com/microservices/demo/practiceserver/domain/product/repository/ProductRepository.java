package com.microservices.demo.practiceserver.domain.product.repository;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    Slice<Product> findAllByIdGreaterThanOrderByIdAsc(Long id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price > (SELECT p1.price FROM Product p1 WHERE p1.id = :cursor) OR (p.price = (SELECT p1.price FROM Product p1 WHERE p1.id = :cursor) AND p.id > :cursor) ORDER BY p.price asc, p.id ASC")
    Slice<Product> findAllByPrice(Long cursor, Pageable pageable);

    Slice<Product> findAllByOrderByPriceAsc(Pageable pageable);
}

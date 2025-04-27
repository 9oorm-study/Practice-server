package com.microservices.demo.practiceserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private double discount;

    @OneToMany(mappedBy = "product",orphanRemoval = true)
    private List<MemberProduct> memberProducts;

    @OneToMany(mappedBy = "product",orphanRemoval = true)
    private List<Cart> carts;

    @OneToMany(mappedBy = "product",orphanRemoval = true)
    private List<ProductImage> productImages;
}

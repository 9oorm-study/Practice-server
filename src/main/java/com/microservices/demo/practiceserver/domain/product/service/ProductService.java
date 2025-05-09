package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequestDTO.CreateProductDTO request);
    List<Product> getProducts();
    Product getProduct(Long productId);
}

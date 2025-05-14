package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product createProduct(ProductRequestDTO.CreateProductDTO request);
    Page<Product> getProducts(Integer page, Integer size);
    Product getProduct(Long productId);
    Product updateProduct(Long productId, ProductRequestDTO.UpdateProductDTO request);
    Product deleteProducts(Long productId);
}

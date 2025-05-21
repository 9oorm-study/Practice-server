package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import org.springframework.data.domain.Slice;

public interface ProductService {
    Product createProduct(ProductRequestDTO.CreateProductDTO request);
    Slice<Product> getProducts(Long cursor, Integer size);
    Product getProduct(Long productId);
    Product updateProduct(Long productId, ProductRequestDTO.UpdateProductDTO request);
    Product deleteProducts(Long productId);
}

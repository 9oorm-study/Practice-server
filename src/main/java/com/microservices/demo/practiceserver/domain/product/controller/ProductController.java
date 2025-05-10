package com.microservices.demo.practiceserver.domain.product.controller;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.dto.ProductResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO.CreateProductResponseDTO> createProduct(@RequestBody ProductRequestDTO.CreateProductDTO request) {
        Product product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.CreateProductResponseDTO.toCreateProductResponseDTO(product));
    }

    @GetMapping("/products/{productId}") // /products/1
    public ProductResponseDTO.ProductDetailResponseDTO getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return ProductResponseDTO.ProductDetailResponseDTO.toProductDetailResponseDTO(product);
    }

    @GetMapping("/products")
    public ProductResponseDTO.ProductListResponseDTO getProducts() {
        List<Product> products = productService.getProducts();
        return ProductResponseDTO.ProductListResponseDTO.toProductListResponseDTO(products);
    }
}

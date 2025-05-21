package com.microservices.demo.practiceserver.domain.product.controller;

import com.microservices.demo.practiceserver.domain.member.service.MemberService;
import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.dto.ProductResponseDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO.CreateProductResponseDTO> createProduct(@RequestBody ProductRequestDTO.CreateProductDTO request) {
        Product product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.CreateProductResponseDTO.toCreateProductResponseDTO(product));
    }

    @GetMapping("/{productId}") // /products/1
    public ProductResponseDTO.ProductDetailResponseDTO getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return ProductResponseDTO.ProductDetailResponseDTO.toProductDetailResponseDTO(product);
    }

    @GetMapping
    public ProductResponseDTO.ProductListResponseDTO getProducts(@RequestParam(defaultValue = "0") Long cursor,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        Slice<Product> products = productService.getProducts(cursor, size);
        return ProductResponseDTO.ProductListResponseDTO.toProductListResponseDTO(products);
    }

    @PatchMapping("/{productId}")
    public ProductResponseDTO.ProductUpdateResponseDTO updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO.UpdateProductDTO request) {
        Product product = productService.updateProduct(productId, request);
        return ProductResponseDTO.ProductUpdateResponseDTO.toProductUpdateResponseDTO(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO.ProductDeleteResponseDTO> deleteProduct(@PathVariable Long productId) {
        Product product = productService.deleteProducts(productId);
        return ResponseEntity.ok().body(ProductResponseDTO.ProductDeleteResponseDTO.toProductDeleteResponseDTO(product));
    }

    // offset, cursor
}

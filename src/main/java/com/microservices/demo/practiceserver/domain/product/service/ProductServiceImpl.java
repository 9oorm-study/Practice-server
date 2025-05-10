package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService productImageService;

    @Override
    public Product createProduct(ProductRequestDTO.CreateProductDTO request) {
        Product product = productRepository.save(request.toProduct());
        createProductImage(request, product);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("상품을 찾지 못했습니다."));
    }

    private void createProductImage(ProductRequestDTO.CreateProductDTO request, Product product) {
        for (String keyName : request.getKeyName()) {
            productImageService.createProductImage(product, keyName);
        }
    }
}

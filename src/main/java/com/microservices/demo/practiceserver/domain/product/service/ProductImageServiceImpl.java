package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.ProductImage;
import com.microservices.demo.practiceserver.domain.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public ProductImage createProductImage(Product product, String keyName) {
        return productImageRepository.save(
                ProductImage.builder()
                        .keyName(keyName)
                        .product(product)
                        .build()
        );
    }
}

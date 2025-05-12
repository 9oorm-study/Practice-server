package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.ProductImage;

public interface ProductImageService {

    ProductImage createProductImage(Product product, String keyName);
}

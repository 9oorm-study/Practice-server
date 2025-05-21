package com.microservices.demo.practiceserver.domain.product.service;

import com.microservices.demo.practiceserver.domain.product.dto.ProductRequestDTO;
import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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
    @Transactional(readOnly = true)
    public Slice<Product> getProducts(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);
        Slice<Product> products;
        if (cursor.equals(0L)) {
            products = productRepository.findAllByOrderByPriceAsc(pageable);
        }
        else {
            products = productRepository.findAllByPrice(cursor, pageable);
        }
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return findProduct(productId);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequestDTO.UpdateProductDTO request) {
        Product product = findProduct(productId);
        product.updateProduct(request.getPrice(), request.getDiscount());
        return product;
    }

    @Override
    public Product deleteProducts(Long productId) {
        Product product = findProduct(productId);
        product.softDelete();
        return product;
    }

    private void createProductImage(ProductRequestDTO.CreateProductDTO request, Product product) {
        if (request.getKeyName() != null) {
            for (String keyName : request.getKeyName()) {
                productImageService.createProductImage(product, keyName);
            }
        }
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("상품을 찾지 못했습니다."));
    }

}

package com.microservices.demo.practiceserver.domain.product.dto;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.ProductImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ProductResponseDTO {

    @Getter
    @Builder
    public static class CreateProductResponseDTO {
        private Long productId;
        private String name;
        private Integer price;
        private Double discount;
        private List<String> images;

        public static CreateProductResponseDTO toCreateProductResponseDTO(Product product) {
            return CreateProductResponseDTO.builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .images(product.getProductImageList().stream().map(ProductImage::getKeyName).toList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductDetailResponseDTO {
        private Long productId;
        private String name;
        private Integer price;
        private Double discount;
        private List<String> images;

        public static ProductDetailResponseDTO toProductDetailResponseDTO(Product product) {
            return ProductDetailResponseDTO.builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .images(product.getProductImageList().stream().map(ProductImage::getKeyName).toList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductListResponseDTO {
        private List<ProductItemResponseDTO> items;

        public static ProductListResponseDTO toProductListResponseDTO(List<Product> products) {
            return ProductResponseDTO.ProductListResponseDTO.builder()
                    .items(products.stream().map(ProductItemResponseDTO::toProductItemResponseDTO).toList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductItemResponseDTO {
        private Long productId;
        private String name;
        private Integer price;
        private Double discount;
        public static ProductItemResponseDTO toProductItemResponseDTO(Product product) {
            return ProductItemResponseDTO.builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .build();
        }
    }
}

package com.microservices.demo.practiceserver.domain.product.dto;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import com.microservices.demo.practiceserver.domain.product.entity.ProductImage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
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
                    .images(product.getProductImageList() != null ? product.getProductImageList().stream().map(ProductImage::getKeyName).toList() : null)
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
        private Integer page;
        private Integer totalPage;

        public static ProductListResponseDTO toProductListResponseDTO(Page<Product> products) {
            return ProductListResponseDTO.builder()
                    .items(products.getContent().stream().map(ProductItemResponseDTO::toProductItemResponseDTO).toList())
                    .page(products.getNumber() + 1)
                    .totalPage(products.getTotalPages())
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

    @Getter
    @Builder
    public static class ProductUpdateResponseDTO {
        private Long productId;
        private Integer price;
        private Double discount;
        public static ProductUpdateResponseDTO toProductUpdateResponseDTO(Product product) {
            return ProductUpdateResponseDTO.builder()
                    .productId(product.getId())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductDeleteResponseDTO {
        private Long productId;
        private LocalDateTime deletedAt;
        public static ProductResponseDTO.ProductDeleteResponseDTO toProductDeleteResponseDTO(Product product) {
            return ProductDeleteResponseDTO.builder()
                    .productId(product.getId())
                    .deletedAt(product.getDeletedAt())
                    .build();
        }
    }
}

package com.microservices.demo.practiceserver.domain.product.dto;

import com.microservices.demo.practiceserver.domain.product.entity.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ProductRequestDTO {

    @Getter
    public static class CreateProductDTO {
        private String name;
        private Integer price;
        private Double discount;
        private List<String> keyName;

        public Product toProduct() {
            return Product.builder()
                    .name(this.name)
                    .price(this.price)
                    .discount(this.discount)
                    .productImageList(new ArrayList<>()) // 변경 부분
                    .build();
        }

    }
}

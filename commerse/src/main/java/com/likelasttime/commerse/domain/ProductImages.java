package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateProductImagesRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    String url;

    String altText;

    Boolean isPrimary;

    Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "option_id")
    ProductOptions productOptions;

    public static ProductImages createProductImages(Products products, CreateProductImagesRequest createProductImagesRequest) {
        return ProductImages.builder()
                .products(products)
                .url(createProductImagesRequest.getUrl())
                .altText(createProductImagesRequest.getAlt_text())
                .isPrimary(createProductImagesRequest.getIs_primary())
                .displayOrder(createProductImagesRequest.getDisplay_order())
                .build();
    }
}

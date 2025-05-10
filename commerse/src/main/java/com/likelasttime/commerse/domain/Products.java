package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String slug;

    String shortDescription;

    String fullDescription;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    Sellers sellers;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brands brands;

    String status;

    public static Products createProducts(CreateProductRequest createProductRequest,
                                      Sellers sellers, Brands brands) {
        return Products.builder()
                .name(createProductRequest.getName())
                .slug(createProductRequest.getSlug())
                .shortDescription(createProductRequest.getShort_description())
                .fullDescription(createProductRequest.getFull_description())
                .sellers(sellers)
                .brands(brands)
                .status(createProductRequest.getStatus())
                .build();
    }
}

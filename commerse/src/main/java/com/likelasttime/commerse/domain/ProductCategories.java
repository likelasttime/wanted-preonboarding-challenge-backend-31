package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateCategoriesRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Categories categories;

    Boolean isPrimary;

    public static ProductCategories createProductCategories(Products products,
                                                                  Categories categories, boolean isPrimary) {
        return ProductCategories.builder()
                .products(products)
                .categories(categories)
                .isPrimary(isPrimary)
                .build();
    }
}

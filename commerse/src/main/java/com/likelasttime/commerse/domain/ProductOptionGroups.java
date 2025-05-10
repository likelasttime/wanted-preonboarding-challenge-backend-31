package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateProductOptionGroupsRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    String name;

    Integer displayOrder;

    public static ProductOptionGroups createProductOptionGroups(Products products,
                                                                 CreateProductOptionGroupsRequest createProductOptionGroupsRequest) {
        return ProductOptionGroups.builder()
                .products(products)
                .name(createProductOptionGroupsRequest.getName())
                .displayOrder(createProductOptionGroupsRequest.getDisplay_order())
                .build();
    }
}

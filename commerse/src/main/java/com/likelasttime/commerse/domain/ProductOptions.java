package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateOptionsRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "option_group_id")
    ProductOptionGroups productOptionGroups;

    String name;

    BigDecimal additionalPrice;

    String sku;

    Integer stock;

    Integer displayOrder;

    public static ProductOptions createProductOptions(ProductOptionGroups productOptionGroups,
                                                      CreateOptionsRequest createOptionsRequest) {
        return ProductOptions.builder()
                .productOptionGroups(productOptionGroups)
                .name(createOptionsRequest.getName())
                .additionalPrice(createOptionsRequest.getAdditional_price())
                .sku(createOptionsRequest.getSku())
                .stock(createOptionsRequest.getStock())
                .displayOrder(createOptionsRequest.getDisplay_order())
                .build();
    }
}

package com.likelasttime.commerse.domain;

import com.likelasttime.commerse.dto.request.CreateProductPricesRequest;
import com.likelasttime.commerse.dto.request.CreateProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPrices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    Products products;

    BigDecimal basePrice;

    BigDecimal salePrice;

    BigDecimal costPrice;

    String currency;

    BigDecimal taxRate;

    public static ProductPrices createProductPrices(Products products, CreateProductPricesRequest createProductPricesRequest) {
        return ProductPrices.builder()
                .products(products)
                .basePrice(createProductPricesRequest.getBase_price())
                .salePrice(createProductPricesRequest.getSale_price())
                .costPrice(createProductPricesRequest.getCost_price())
                .currency(createProductPricesRequest.getCurrency())
                .taxRate(createProductPricesRequest.getTax_rate())
                .build();
    }
}

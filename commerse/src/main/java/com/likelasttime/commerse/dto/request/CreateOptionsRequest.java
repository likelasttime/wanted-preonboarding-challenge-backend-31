package com.likelasttime.commerse.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOptionsRequest {
    private String name;

    private BigDecimal additional_price;

    private String sku;

    private Integer stock;

    private Integer display_order;
}

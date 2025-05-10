package com.likelasttime.commerse.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductPricesRequest {
    private BigDecimal base_price;

    private BigDecimal sale_price;

    private BigDecimal cost_price;

    private String currency;

    private BigDecimal tax_rate;
}

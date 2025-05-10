package com.likelasttime.commerse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDetailsRequest {
    private BigDecimal weight;

    // json
    private Map<String, Integer> dimensions;

    private String materials;

    private String country_of_origin;

    private String warranty_info;

    private String care_instructions;

    private Map<String, Object> additional_info;
}

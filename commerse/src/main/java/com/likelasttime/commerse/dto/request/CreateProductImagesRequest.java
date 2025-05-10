package com.likelasttime.commerse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductImagesRequest {
    private String url;

    private String alt_text;

    private Boolean is_primary;

    private Integer display_order;

    private Integer option_id;
}

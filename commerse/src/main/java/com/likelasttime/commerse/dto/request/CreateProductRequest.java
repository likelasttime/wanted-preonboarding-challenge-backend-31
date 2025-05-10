package com.likelasttime.commerse.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateProductRequest {
    private String name;

    private String slug;

    private String short_description;

    private String full_description;

    private Long seller_id;

    private Long brand_id;

    private String status;

    private CreateProductDetailsRequest detail;

    private CreateProductPricesRequest price;

    private List<CreateCategoriesRequest> categories;

    private List<CreateProductOptionGroupsRequest> option_groups;

    private List<CreateProductImagesRequest> images;

    private List<Long> tags;
}

package com.likelasttime.commerse.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriesRequest {
    private Long category_id;

    private boolean is_primary;
}

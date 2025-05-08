package com.likelasttime.commerse.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaginationResponse<T> {
    private T items;

    private Pagination pagination;
}

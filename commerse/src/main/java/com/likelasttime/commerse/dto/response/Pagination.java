package com.likelasttime.commerse.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pagination {
    private int totalItems;

    private int totalPages;

    private int currentPage;

    private int perPage;
}

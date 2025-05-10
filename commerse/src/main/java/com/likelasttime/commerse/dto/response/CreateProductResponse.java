package com.likelasttime.commerse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateProductResponse {
    private Long id;

    private String name;

    private String slug;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}

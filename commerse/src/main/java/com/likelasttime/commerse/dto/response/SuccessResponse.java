package com.likelasttime.commerse.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponse<T> {
    private boolean success = true;

    private T data;

    private String message;
}

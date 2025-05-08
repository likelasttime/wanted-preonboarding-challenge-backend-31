package com.likelasttime.commerse.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorBody {
    private String code;

    private String message;
}

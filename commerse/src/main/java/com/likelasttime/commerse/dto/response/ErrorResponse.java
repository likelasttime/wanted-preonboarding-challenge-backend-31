package com.likelasttime.commerse.dto.response;

import com.likelasttime.commerse.exception.CommonErrorCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private boolean success = false;

    private ErrorBody errorBody;
}

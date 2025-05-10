package com.likelasttime.commerse.dto.response;

import com.likelasttime.commerse.exception.CommonErrorCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorBody {
    private String code;

    private String message;

    public ErrorBody(final CommonErrorCode commonErrorCode) {
        this.code = commonErrorCode.getCode();
        this.message = commonErrorCode.getMessage();
    }
}

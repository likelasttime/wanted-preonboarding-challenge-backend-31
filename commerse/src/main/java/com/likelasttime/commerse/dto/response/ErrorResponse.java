package com.likelasttime.commerse.dto.response;

import com.likelasttime.commerse.exception.CommonErrorCode;
import lombok.*;

@AllArgsConstructor
@Getter
public class ErrorResponse extends RuntimeException {
    //private final String code;

    //private final String message;
    private boolean success;

    private ErrorBody error;

    /*public ErrorResponse(final CommonErrorCode commonErrorCode) {
        this.code = commonErrorCode.getCode();
        this.message = commonErrorCode.getMessage();
    }*/
}

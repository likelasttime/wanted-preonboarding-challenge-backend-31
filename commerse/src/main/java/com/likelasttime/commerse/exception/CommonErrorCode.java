package com.likelasttime.commerse.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum CommonErrorCode {
    INVALID_INPUT("INVALID_INPUT", "잘못된 입력 데이터"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청한 리소스를 찾을 수 없음"),
    UNAUTHORIZED("UNAUTHORIZED", "인증되지 않은 요청"),
    FORBIDDEN("FORBIDDEN", "권한이 없는 요청"),
    CONFLICT("CONFLICT", "리소스 충돌 발생"),
    INTERNAL_ERROR("INTERNAL_ERROR", "서버 내부 오류");

    private String code;

    private String description;

    private CommonErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

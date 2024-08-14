package com.flow.blockingextension.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    INVALID_EXTENSION(HttpStatus.NOT_FOUND ,"E-0001", "존재하지 않는 확장자입니다."),
    FAIL_SAVING(HttpStatus.INTERNAL_SERVER_ERROR, "E-0002", "저장에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public ApplicationException build() {
        return new ApplicationException(httpStatus, code, message);
    }

    public ApplicationException build(Object ...args) {
        return new ApplicationException(httpStatus, code, message.formatted(args));
    }
}

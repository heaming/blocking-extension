package com.flow.blockingextension.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ServerExceptionResponse> applicationExceptionHandler(ApplicationException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ServerExceptionResponse(ex.getCode(), ex.getMessage()));
    }

    public record ServerExceptionResponse(String code, String message) {}
}

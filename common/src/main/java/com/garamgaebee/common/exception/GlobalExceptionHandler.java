package com.garamgaebee.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ErrorDTO handleException(Exception e) {
        log.error("handleException: {}", e.getMessage());
        log.error(String.valueOf(e.getCause()));
        return ErrorDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<BaseException> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new BaseException(BaseErrorCode.METHOD_NOT_ALLOWED));
    }

    @ResponseBody
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<BaseException> handleException(BaseException e) {
        log.error("handleCustomException: {}", e.getMessage());
        return ResponseEntity
                .status(e.getBaseErrorCode().getCode())
                .body(new BaseException(e.getBaseErrorCode()));
    }
}
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
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDTO> handleException(final Exception e) {
        log.error("handleException: {}", e.getMessage());
        log.error(String.valueOf(e.getCause()));
        final ErrorDTO errorDTO = new ErrorDTO(BaseErrorCode.SERVER_ERROR);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDTO);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorDTO> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        final ErrorDTO errorDTO = new ErrorDTO(BaseErrorCode.METHOD_NOT_ALLOWED);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorDTO);
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ErrorDTO> handleException(BaseException e) {
        log.error("handleCustomException: {}", e.getMessage());
        final ErrorDTO errorDTO = new ErrorDTO(e);
        return ResponseEntity
                .status(e.getBaseErrorCode().getCode())
                .body(errorDTO);
    }
}
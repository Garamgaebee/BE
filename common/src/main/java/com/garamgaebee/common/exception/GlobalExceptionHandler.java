package com.garamgaebee.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {BaseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(BaseException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder()
                .code(exception.getBaseErrorCode().getCode())
                .message(exception.getBaseErrorCode().getMessage())
                .build();
    }

}

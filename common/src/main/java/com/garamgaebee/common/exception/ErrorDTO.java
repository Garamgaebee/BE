package com.garamgaebee.common.exception;

import lombok.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private final boolean isSuccess;
    private final String message;

    public ErrorDTO(BaseException e) {
        this.isSuccess = e.getBaseErrorCode().isSuccess();
        this.message = e.getBaseErrorCode().getMessage();
    }

    public ErrorDTO(BaseErrorCode baseErrorCode) {
        this.isSuccess = baseErrorCode.isSuccess();
        this.message =  baseErrorCode.getMessage();
    }
}
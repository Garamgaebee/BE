package com.garamgaebee.common.exception;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private BaseErrorCode baseErrorCode;
}

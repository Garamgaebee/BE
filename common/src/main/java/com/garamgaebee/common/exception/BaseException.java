package com.garamgaebee.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseErrorCode baseErrorCode;


}

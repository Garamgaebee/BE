package com.garamgaebee.common.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private final String code;
    private final String message;
}
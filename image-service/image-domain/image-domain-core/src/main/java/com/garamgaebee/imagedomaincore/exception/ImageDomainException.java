package com.garamgaebee.imagedomaincore.exception;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;

public class ImageDomainException extends BaseException {
    public ImageDomainException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}

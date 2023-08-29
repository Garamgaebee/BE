package com.garamgaebee.teamdomainservice.exception;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;

public class TeamNotFoundException extends BaseException {

    public TeamNotFoundException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}

package com.garamgaebee.teamdomainservice.exception;


import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;

public class TeamDomainException extends BaseException {

    public TeamDomainException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}

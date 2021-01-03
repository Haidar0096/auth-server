package com.nlimits.authserver.application.user.application.service.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserManagementException extends RuntimeException {
    ErrorCode errorCode;

    public UserManagementException(ErrorCode errorCode) {
        super(errorCode.getValue());
        this.errorCode = errorCode;
    }
}

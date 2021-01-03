package com.nlimits.authserver.application.user.application.service.exception;

public class UserManagementException extends RuntimeException {
    public UserManagementException(ErrorCode errorCode) {
        super(errorCode.getValue());
    }
}

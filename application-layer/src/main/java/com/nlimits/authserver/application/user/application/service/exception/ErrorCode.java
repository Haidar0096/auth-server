package com.nlimits.authserver.application.user.application.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    GENERAL_ERROR("GENERAL_ERROR"),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS"),
    USER_DOES_NOT_EXIST("USER_DOES_NOT_EXIST");

    private final String value;
}

//todo write documentation for this class
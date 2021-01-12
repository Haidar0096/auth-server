package com.nlimits.authserver.adapter.input.web.user;

import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.common.web.Response;
import com.nlimits.authserver.common.web.VoidResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * An exception handler class for exceptions thrown during processing data
 * for User model objects
 */
@ControllerAdvice
public class GlobalUserExceptionHandler {

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<Response<VoidResponseData>> handleUserManagementException(UserManagementException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new Response<>(false,
                        null,
                        exception.getMessage(),
                        exception.getErrorCode().getValue())
        );
    }
}



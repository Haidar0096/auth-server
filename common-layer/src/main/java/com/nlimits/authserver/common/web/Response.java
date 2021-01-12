package com.nlimits.authserver.common.web;

import lombok.Value;

/**
 * This class is the standard model for the response that shall be sent back to the user
 */
@Value
public class Response<T extends ResponseData> {
    Boolean success;
    T data;
    String message;
    String ErrorCode;
}

//todo write documentation for this class
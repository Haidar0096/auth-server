package com.nlimits.authserver.common.web;

import lombok.Value;

@Value
public class Response<T extends ResponseData> {
    Boolean success;
    T data;
    String message;
    String ErrorCode;
}

//todo write documentation for this class
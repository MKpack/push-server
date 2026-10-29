package com.zack.push.server.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    SUCCESS(0, HttpStatus.OK, "success"),

    INTERNAL_ERROR(9000, HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");

    private final int code;
    private final HttpStatus httpStatus;
    private final String defaultMsg;

    ErrorCode(int code, HttpStatus httpStatus, String defaultMsg) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.defaultMsg = defaultMsg;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }
}

package com.zack.push.server.common;

public record ApiResponse<T>(int code, String msg, T data) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), "success", data);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), "success", null);
    }

    public static ApiResponse<Void> fail(ErrorCode errorCode, String msg) {
        return new ApiResponse<>(errorCode.getCode(), msg ,null);
    }
}

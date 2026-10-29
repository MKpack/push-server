package com.zack.push.server.controller;

import com.zack.push.server.common.ApiResponse;
import com.zack.push.server.common.BusinessException;
import com.zack.push.server.common.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 业务异常，预期内的打warn */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException e) {
        log.warn("business error: {} - {}", e.getErrorCode(), e.getMessage());
        return build(e.getErrorCode(), e.getMessage());
    }

    /** 系统内部错误，兜底 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexcepted(Exception e) {
        log.error("unexpected error", e);
        return build(ErrorCode.INTERNAL_ERROR, ErrorCode.INTERNAL_ERROR.getDefaultMsg());
    }


    private ResponseEntity<ApiResponse<Void>> build(ErrorCode ec, String msg) {
        return ResponseEntity.status(ec.getHttpStatus()).body(ApiResponse.fail(ec, msg));
    }
}

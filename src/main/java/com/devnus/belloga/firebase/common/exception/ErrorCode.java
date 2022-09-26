package com.devnus.belloga.firebase.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    REQUEST_PARAMETER_BIND_FAILED(HttpStatus.BAD_REQUEST, "REQ_001", "PARAMETER_BIND_FAILED");

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }
}



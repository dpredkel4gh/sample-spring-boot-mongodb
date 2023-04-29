package com.example.sample.exception;

import lombok.Getter;

public class ServiceRuntimeException extends RuntimeException {

    @Getter
    private int status;

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ServiceRuntimeException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

}

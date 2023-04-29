package com.example.sample.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VALIDATION_ERROR(1400),
    REQUESTED_SIZE_INVALID(1401);

    private int value;

}

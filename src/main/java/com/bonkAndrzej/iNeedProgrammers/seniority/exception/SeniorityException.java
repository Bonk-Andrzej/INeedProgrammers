package com.bonkAndrzej.iNeedProgrammers.seniority.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")

public class SeniorityException extends Exception {
    public SeniorityException(String message) {
        super(message);
    }
}

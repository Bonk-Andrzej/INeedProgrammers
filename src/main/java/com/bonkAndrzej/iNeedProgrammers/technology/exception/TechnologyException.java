package com.bonkAndrzej.iNeedProgrammers.technology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class TechnologyException extends Exception {
    public TechnologyException(String message) {
        super(message);
    }
}

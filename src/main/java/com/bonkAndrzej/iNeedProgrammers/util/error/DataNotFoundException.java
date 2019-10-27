package com.bonkAndrzej.iNeedProgrammers.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}

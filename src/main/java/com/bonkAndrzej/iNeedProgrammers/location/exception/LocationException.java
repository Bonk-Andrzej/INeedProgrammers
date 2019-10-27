package com.bonkAndrzej.iNeedProgrammers.location.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class LocationException extends Exception {
    public LocationException(String message) {
        super(message);
    }
}

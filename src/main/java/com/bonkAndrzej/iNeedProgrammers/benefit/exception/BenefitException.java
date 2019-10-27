package com.bonkAndrzej.iNeedProgrammers.benefit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class BenefitException extends Exception {

    public BenefitException(String message) {
        super(message);
    }

}

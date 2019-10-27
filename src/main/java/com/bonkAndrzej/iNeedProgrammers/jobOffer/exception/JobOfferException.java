package com.bonkAndrzej.iNeedProgrammers.jobOffer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class JobOfferException extends Exception {
    public JobOfferException(String message) {
        super(message);
    }
}

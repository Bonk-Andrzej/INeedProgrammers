package com.bonkAndrzej.iNeedProgrammers.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class AccountException extends Exception {

    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }
}

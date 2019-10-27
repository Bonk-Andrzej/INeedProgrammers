package com.bonkAndrzej.iNeedProgrammers.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class AccountException extends Exception {

    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }
}

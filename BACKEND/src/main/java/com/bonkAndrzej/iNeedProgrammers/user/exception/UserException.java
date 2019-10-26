package com.bonkAndrzej.iNeedProgrammers.user.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}

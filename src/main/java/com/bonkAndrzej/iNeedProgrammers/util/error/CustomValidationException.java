package com.bonkAndrzej.iNeedProgrammers.util.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class CustomValidationException extends Exception {

    public CustomValidationException(String message) {
        super(message);
    }

}

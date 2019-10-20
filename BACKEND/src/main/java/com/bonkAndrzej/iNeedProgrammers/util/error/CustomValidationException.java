package com.bonkAndrzej.iNeedProgrammers.util.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class CustomValidationException extends Exception {

    private String field = "";

    private String code = "";

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, String field, String code) {
        super(message);
        this.field = field;
        this.code = code;
    }
}

package com.bonkAndrzej.iNeedProgrammers.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class CategoryException extends Exception {

    public CategoryException(String message) {
        super(message);
    }
}

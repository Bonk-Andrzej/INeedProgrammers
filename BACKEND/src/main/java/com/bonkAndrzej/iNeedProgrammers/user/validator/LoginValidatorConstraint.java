package com.bonkAndrzej.iNeedProgrammers.user.validator;

import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class LoginValidatorConstraint implements ConstraintValidator<LoginValidator, String> {

    private final UserService userService;

    @Override public void initialize(LoginValidator constraintAnnotation) {

    }

    @Override public boolean isValid(String login, ConstraintValidatorContext context) {
        return !login.isBlank() && userService.findOneByLogin(login).isEmpty();
    }
}

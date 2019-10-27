package com.bonkAndrzej.iNeedProgrammers.user.validator;

import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UserValidatorConstraint implements ConstraintValidator<UserValidator, Long> {

    private final UserService userService;

    @Override
    public void initialize(UserValidator constraintAnnotation) {
    }

    @Override public boolean isValid(Long userId, ConstraintValidatorContext context) {
        return userId != null && userService.findOneById(userId).isPresent();
    }
}

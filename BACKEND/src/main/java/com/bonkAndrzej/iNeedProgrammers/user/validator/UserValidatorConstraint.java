package com.bonkAndrzej.iNeedProgrammers.user.validator;

import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UserValidator implements ConstraintValidator<UserValidatorConstr, Long> {

    private final UserService userService;

    @Override
    public void initialize(UserValidatorConstr constraintAnnotation) {
    }

    @Override public boolean isValid(Long userId, ConstraintValidatorContext context) {
        return userId != null && userService.findOneById(userId).isPresent();
    }
}

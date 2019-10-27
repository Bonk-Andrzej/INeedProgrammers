package com.bonkAndrzej.iNeedProgrammers.user.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RolesValidatorConstraint.class)
public @interface RolesValidator {
    String message() default "Wrong Roles Names";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

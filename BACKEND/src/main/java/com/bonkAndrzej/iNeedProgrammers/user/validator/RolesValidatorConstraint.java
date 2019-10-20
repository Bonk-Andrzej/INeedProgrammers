package com.bonkAndrzej.iNeedProgrammers.user.validator;

import com.bonkAndrzej.iNeedProgrammers.user.role.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class RolesValidatorConstraint implements ConstraintValidator<RolesValidator, String> {

    private final RoleService roleService;

    @Override
    public void initialize(RolesValidator constraintAnnotation) {
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(String role, ConstraintValidatorContext context) {
        return role != null && roleService.getRoleByName(role).isPresent();
    }
}

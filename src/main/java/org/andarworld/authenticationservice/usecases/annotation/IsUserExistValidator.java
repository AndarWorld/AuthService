package org.andarworld.authenticationservice.usecases.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.andarworld.authenticationservice.api.exceptions.UsernameAlreadyExist;
import org.andarworld.authenticationservice.usecases.UserService;

@RequiredArgsConstructor
public class IsUserExistValidator implements ConstraintValidator<IsUserExistConstraint, String> {

   private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userService.isUserExist(value)) {
            throw new UsernameAlreadyExist("Username already exist!");
        } else return true;
    }
}

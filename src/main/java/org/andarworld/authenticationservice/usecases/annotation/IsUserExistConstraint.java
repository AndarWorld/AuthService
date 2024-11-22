package org.andarworld.authenticationservice.usecases.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUserExistValidator.class)
@Documented
public @interface IsUserExistConstraint {
    String message() default "This user is already exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

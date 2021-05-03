package com.cgi.dentistapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Boilerplate code for custom validation

@Constraint(validatedBy = LocalDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface LocalDateConstraint {
    String message() default "date error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

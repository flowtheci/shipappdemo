package com.cgi.dentistapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = DentistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface DentistConstraint {
    String message() default "Hambaarsti ei leitud!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

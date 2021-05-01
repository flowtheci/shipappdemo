package com.cgi.dentistapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LocalDateValidator implements ConstraintValidator<LocalDateConstraint, LocalDate> {

    @Override
    public void initialize(LocalDateConstraint selectedDate){
    }

    // This custom validation is needed as @Future tag does not support LocalDate, as LocalDate is
    // a timestamp without any timezones.

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext cxt) {
        if(date.compareTo(LocalDate.now()) <= 0){
            return false;
        }
        else return true;
    }
}

package com.cgi.dentistapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LocalDateValidator implements ConstraintValidator<LocalDateConstraint, LocalDate> {

    @Override
    public void initialize(LocalDateConstraint selectedDate){
    }

    // Checks selected date, returns valid if it's at least 1 day in the future
    // This custom validation is needed because @Future tag does not support LocalDate, as LocalDate is
    // a timestamp without any timezones.

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext cxt) {
        if (date != null) // Technically unneeded as @NotNull could be set to run first but this is easier and safer
        {
            if(date.compareTo(LocalDate.now()) <= 0){
                return false;
            }
            else return true;
        }
        else return false;
    }
}

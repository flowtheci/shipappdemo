package com.cgi.dentistapp.validator;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class DentistValidator implements ConstraintValidator<DentistConstraint, String> {

    @Override
    public void initialize(DentistConstraint dentistName){
    }

    @Autowired
    private DentistVisitService dentistVisitService;

    // this is definitely not a smart way to do this, currently dentist names are hardcoded in, should be swapped out
    // for either a table of dentist names or some other system in the future.
    @Override
    public boolean isValid(String s, ConstraintValidatorContext cxt) {
        return dentistVisitService.getDentistList().contains(s);
    }
}

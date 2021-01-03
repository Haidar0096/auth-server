package com.nlimits.authserver.application.user.domain.annotation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailStringValidator implements ConstraintValidator<EmailString, String> {

    public void initialize(EmailString constraint) {
    }

    public boolean isValid(String emailString, ConstraintValidatorContext context) {
        return EmailValidator
                .getInstance()
                .isValid(emailString);
    }
}

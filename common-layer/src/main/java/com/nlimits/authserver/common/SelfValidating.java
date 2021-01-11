package com.nlimits.authserver.common;


import javax.validation.*;
import java.util.Set;

/**
 * Classes that extend this class can have javax or custom validation annotations.
 * They can then call validateSelf() method in the constructor to validate the annotated fields of the class
 *
 * @param <T>
 */
public abstract class SelfValidating<T> {
    private final Validator validator;

    public SelfValidating() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}

//todo write documentation for this class

//todo check warnings in this class

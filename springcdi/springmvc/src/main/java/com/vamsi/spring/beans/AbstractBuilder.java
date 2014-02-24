package com.vamsi.spring.beans;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class AbstractBuilder<T> {

    private static Validator validator = 
        Validation.buildDefaultValidatorFactory().getValidator();

    protected abstract T buildInternal();

    public T build() throws ConstraintViolationException {

        T object = buildInternal();

        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(
                new HashSet<ConstraintViolation<?>>(violations));
        }

        return object;
    }
}

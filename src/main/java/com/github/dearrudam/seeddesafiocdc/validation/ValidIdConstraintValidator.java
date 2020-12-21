package com.github.dearrudam.seeddesafiocdc.validation;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;

@Component
public class ValidIdConstraintValidator implements ConstraintValidator<ValidId, Serializable> {

    private final EntityManager manager;
    private ValidId constraintAnnotation;

    public ValidIdConstraintValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ValidId constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Serializable value, ConstraintValidatorContext context) {
        if (value == null)
            return this.constraintAnnotation.canBeNull() ? true : false;

        boolean exists = manager.find(this.constraintAnnotation.entityClass(), value) != null;

        return exists;
    }
}

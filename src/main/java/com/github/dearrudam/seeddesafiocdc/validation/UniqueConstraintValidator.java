package com.github.dearrudam.seeddesafiocdc.validation;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {

    private final EntityManager entityManager;
    private Class<?> entityClass;
    private String fieldName;

    public UniqueConstraintValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Long count = entityManager
                .createQuery("select count(*) from " + entityClass.getName() + " e " +
                        "where e." + fieldName + "= :fieldValue ", Long.class)
                .setParameter("fieldValue", value).getSingleResult();

        return count == 0l;
    }
}

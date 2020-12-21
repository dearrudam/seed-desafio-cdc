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
        
        StringBuilder jpql = new StringBuilder("select count(*) from " + entityClass.getName() + " e ");
        jpql.append("where ");
        if (value instanceof String) {
            jpql.append("lower(e." + fieldName + ") = lower(:fieldValue)");
        }else{

            jpql.append("e." + fieldName + " = :fieldValue");
        }
        Long count = entityManager
                .createQuery(jpql.toString(), Long.class)
                .setParameter("fieldValue", value).getSingleResult();

        return count == 0l;
    }
}

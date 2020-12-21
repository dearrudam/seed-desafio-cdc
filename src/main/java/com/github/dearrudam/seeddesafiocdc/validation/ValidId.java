package com.github.dearrudam.seeddesafiocdc.validation;

import org.springframework.http.HttpStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidIdConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {

    String message() default "{com.github.dearrudam.seeddesafiocdc.shared.ValidId.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entityClass();

    boolean canBeNull() default false;

    boolean throwCustomConstraintViolation() default false;

    HttpStatus httpStatus() default HttpStatus.BAD_REQUEST;
}

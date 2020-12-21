package com.github.dearrudam.seeddesafiocdc.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationHandler {

    private final MessageSource messageSource;

    @Autowired
    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ValidationErrors> handler(MethodArgumentTypeMismatchException exception, Locale locale) {
        ValidationErrors errors = new ValidationErrors(
                List.of(),
                List.of(
                        new com.github.dearrudam.seeddesafiocdc.validation.FieldError(
                                exception.getName(),
                                this.messageSource.getMessage(
                                        exception.getErrorCode(),
                                        new Object[0],
                                        exception.getMostSpecificCause().getLocalizedMessage(),
                                        locale)
                        )
                )

        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationErrors> handle(ConstraintViolationException exception, Locale locale) {
        ValidationErrors errors = new ValidationErrors(
                List.of(),
                exception.getConstraintViolations().stream()
                        .map(c -> new com.github.dearrudam.seeddesafiocdc.validation.FieldError(
                                getFieldNameOf(c),
                                c.getMessage()
                        )).collect(Collectors.toList())
        );
        return ResponseEntity.badRequest().body(errors);

    }

    private String getFieldNameOf(javax.validation.ConstraintViolation<?> constraintViolation) {
        if (constraintViolation.getRootBeanClass().isAnnotationPresent(Validated.class)) {
            String[] propertyPath = constraintViolation.getPropertyPath().toString().split("\\.", -1);
            return propertyPath[propertyPath.length - 1];
        }
        return constraintViolation.getPropertyPath().toString();
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ValidationErrors> handle(BindException exception, Locale locale) {
        ValidationErrors errors = buildValidationErrors(locale,
                exception.getBindingResult().getGlobalErrors(),
                exception.getBindingResult().getFieldErrors());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrors> handle(MethodArgumentNotValidException exception, Locale locale) {

        ValidationErrors errors = buildValidationErrors(locale,
                exception.getBindingResult().getGlobalErrors(),
                exception.getBindingResult().getFieldErrors());

        return ResponseEntity.badRequest().body(errors);
    }

    private ValidationErrors buildValidationErrors(final Locale locale,
                                                   final List<ObjectError> globalErrors,
                                                   final List<FieldError> fieldErrors) {
        ValidationErrors errors = new ValidationErrors(
                globalErrors.stream().map(error -> this.getErrorMessage(locale, error)).collect(Collectors.toList()),
                fieldErrors.stream().map(error -> this.getErrorMessage(locale, error)).collect(Collectors.toList())
        );
        return errors;
    }

    private String getErrorMessage(Locale locale, ObjectError objectError) {
        return getLocalizedMessage(objectError, locale);
    }

    private com.github.dearrudam.seeddesafiocdc.validation.FieldError getErrorMessage(Locale locale, FieldError fieldError) {
        return new com.github.dearrudam.seeddesafiocdc.validation.FieldError(
                fieldError.getField(),
                getLocalizedMessage(fieldError, locale)
        );
    }

    private String getLocalizedMessage(MessageSourceResolvable messageSource, Locale locale) {
        return this.messageSource.getMessage(messageSource, locale);
    }

}

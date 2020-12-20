package com.github.dearrudam.seeddesafiocdc.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

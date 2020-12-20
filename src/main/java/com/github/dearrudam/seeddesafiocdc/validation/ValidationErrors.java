package com.github.dearrudam.seeddesafiocdc.validation;

import java.util.List;

public class ValidationErrors {
    private final List<String> globalErrors;
    private final List<FieldError> fieldErrors;

    public ValidationErrors(List<String> globalErrors, List<FieldError> fieldErrors) {
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}

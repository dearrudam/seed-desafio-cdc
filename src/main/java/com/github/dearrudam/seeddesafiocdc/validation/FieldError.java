package com.github.dearrudam.seeddesafiocdc.validation;

public class FieldError {

    private String field;
    private String message;

    public FieldError(String field, String errorMessage) {
        this.field = field;
        this.message = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

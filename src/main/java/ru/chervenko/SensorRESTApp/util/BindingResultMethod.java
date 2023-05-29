package ru.chervenko.SensorRESTApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class BindingResultMethod {
    public static String buildErrorsStringForBindindResult(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMessage.append(error.getField())
                    .append(" = ").append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMessage.toString();
    }
}

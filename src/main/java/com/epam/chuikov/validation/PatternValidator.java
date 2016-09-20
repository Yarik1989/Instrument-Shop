package com.epam.chuikov.validation;

import com.epam.chuikov.validation.annotation.PatternValidation;
import com.epam.chuikov.validation.api.Validator;

import java.lang.annotation.Annotation;

/**
 * @author Alexander
 */
public class PatternValidator implements Validator {
    @Override
    public String validate(Object field, Annotation annotation) {
        if (annotation.annotationType() != PatternValidation.class) {
            return "Field is not valid";
        }
        PatternValidation patternValidation = (PatternValidation) annotation;
        String errorMessage = patternValidation.errorMessage();
        if (field.getClass() != String.class) {
            return errorMessage;
        }
        String pattern = patternValidation.regex();
        String valueString = field.toString();
        if (!valueString.matches(pattern)) {
            return errorMessage;
        }
        return "";
    }
}

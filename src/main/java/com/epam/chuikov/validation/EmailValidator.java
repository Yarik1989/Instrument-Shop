package com.epam.chuikov.validation;


import com.epam.chuikov.validation.annotation.EmailValidation;
import com.epam.chuikov.validation.api.Validator;

import java.lang.annotation.Annotation;

public class EmailValidator implements Validator {
    @Override
    public String validate(Object field, Annotation annotation) {
        if(annotation.annotationType() != EmailValidation.class){
            return "Field is not valid";
        }
        EmailValidation patternValidation = (EmailValidation) annotation;
        String errorMessage = patternValidation.errorMessage();
        if(field.getClass() != String.class){
            return errorMessage;
        }
        String pattern = patternValidation.regex();
        String valueString = field.toString();
        if(!valueString.matches(pattern)){
            return errorMessage;
        } else {
            return "";
        }
    }
}

package com.epam.chuikov.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.epam.chuikov.validation.PatternValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CustomValidator(validatorClass = PatternValidator.class)
public @interface PatternValidation {
    String regex();

    String errorMessage() default "Invalid field value";
}

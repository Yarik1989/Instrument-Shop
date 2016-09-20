package com.epam.chuikov.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.epam.chuikov.validation.EmailValidator;

/**
 * @author Alexander
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CustomValidator(validatorClass = EmailValidator.class)
public @interface EmailValidation {
    String regex() default "\\S+@\\S+\\.\\S+";

    String errorMessage() default "Invalid email value";
}

package com.epam.chuikov.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.epam.chuikov.validation.NotBlankValidator;

/**
 * @author Alexander
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@CustomValidator(validatorClass = NotBlankValidator.class)
public @interface NotBlank {
}

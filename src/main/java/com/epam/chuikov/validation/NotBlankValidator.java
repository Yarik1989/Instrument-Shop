package com.epam.chuikov.validation;

import java.lang.annotation.Annotation;

import com.epam.chuikov.validation.api.Validator;

/**
 * @author Alexander
 */
public class NotBlankValidator implements Validator {
    @Override
    public String validate(Object value, Annotation pattern) {
        return value == null ? "blank field" : "";
    }
}

package com.epam.chuikov.validation.api;

import java.lang.annotation.Annotation;

/**
 * @author Oleksandr_Onsha
 */
public interface Validator{
    String validate(Object value, Annotation pattern);
}

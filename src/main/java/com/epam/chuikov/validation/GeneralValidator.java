package com.epam.chuikov.validation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import com.epam.chuikov.dto.UserBean;
import com.epam.chuikov.validation.annotation.CustomValidator;
import com.epam.chuikov.validation.api.Validateable;
import com.epam.chuikov.validation.api.Validator;

public class GeneralValidator {
    public Map<String, String> validate(Validateable validateable) {
        List<Field> fields = getAllFields(validateable.getClass());
        Map<String, String> errors = new HashMap<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if(annotation.annotationType().isAnnotationPresent(CustomValidator.class)){
                        Validator v = (Validator) annotation.annotationType()
                                .getAnnotation(CustomValidator.class).validatorClass().newInstance();
                        String error = v.validate(field.get(validateable), annotation);
                        if(!error.isEmpty()){
                            errors.put(field.getName(), error);
                            break;
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return errors;
    }

    private List<Field> getFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        allFields.addAll(getFields(clazz));
        while(clazz.getSuperclass() != Object.class){
            allFields.addAll(getFields(clazz.getSuperclass()));
            clazz = clazz.getSuperclass();
        }
        return allFields;
    }

    public static void main(String[] args) {
        System.out.println(new GeneralValidator().validate(new UserBean("dsa", "dsa", "dsa", "dsa", true)));
    }
}

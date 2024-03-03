package com.example.task.presentation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
    private String field;

    private String confirmField;

    private String message;

    @Override
    public void initialize(Confirm constraintAnnotation) {
        field = constraintAnnotation.field();
        confirmField = constraintAnnotation.confirmField();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
        boolean matched = Objects.equals(fieldValue, confirmFieldValue);
        if (matched) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field).addConstraintViolation();
            return false;
        }
    }
}

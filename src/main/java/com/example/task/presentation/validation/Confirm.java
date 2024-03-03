package com.example.task.presentation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.task.presentation.validation.Confirm.List;

@Documented
@Constraint(validatedBy = { ConfirmValidator.class })
@Target({ TYPE, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface Confirm {
    String message() default "{com.example.task.presentation.validation.Confirm.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String confirmField();

    @Target({ TYPE, ANNOTATION_TYPE, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Confirm[] value();
    }
}

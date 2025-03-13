package com.kids.collection.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistInDBValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistInDatabase {
    public String message() default "the selected value does not exists";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    public String table();
    public String column();
}

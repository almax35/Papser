package com.example.parser.utils;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = PasswordMatcherValidator.class)
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatcher {
    String message() default "Введённые пароли не совпадают";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

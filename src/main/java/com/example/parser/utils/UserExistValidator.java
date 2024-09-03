package com.example.parser.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExistValidator {
    String message() default "Пользователь с данным логином уже зарегестрирован";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

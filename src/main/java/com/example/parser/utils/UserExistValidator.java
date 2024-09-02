package com.example.parser.utils;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExistValidator {
}

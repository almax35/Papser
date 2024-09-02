package com.example.parser.utils;

import com.example.parser.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto, ConstraintValidatorContext constraintValidatorContext) {
        return userRegistrationDto.getVerifyPassword().equals(userRegistrationDto.getPassword());
    }
}

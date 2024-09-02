package com.example.parser.utils;

import com.example.parser.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UserExistValidator,String> {
    private final UserService userService;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isUserExist(username);
    }
}

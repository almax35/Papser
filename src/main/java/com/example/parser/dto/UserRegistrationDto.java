package com.example.parser.dto;

import com.example.parser.utils.PasswordMatcher;
import com.example.parser.utils.UserExistValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@PasswordMatcher
@Valid
public class UserRegistrationDto {
    @NotBlank
    @UserExistValidator
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String verifyPassword;
}

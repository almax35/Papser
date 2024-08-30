package com.example.parser.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String login;
    private String password;
    private String verifyPassword;
}

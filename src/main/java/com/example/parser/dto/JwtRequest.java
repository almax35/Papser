package com.example.parser.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;

}

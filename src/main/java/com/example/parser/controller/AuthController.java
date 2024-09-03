package com.example.parser.controller;

import com.example.parser.dto.JwtRequest;
import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.service.auth.AuthService;
import com.example.parser.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    public String creatAuthToken(@Valid @ModelAttribute("jwtToken") JwtRequest jwtRequest) throws BadCredentialsException{
        String token=authService.getToken(jwtRequest);
        return "redirect:/table";
    }
    @PostMapping("/registration")
    public String createUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:/table";
    }
    @GetMapping("/registration")
    public String showRegisterPage(){
        return "registration";
    }
    @GetMapping("/auth")
    public String showAuthPage(){
        return "auth";
    }
}


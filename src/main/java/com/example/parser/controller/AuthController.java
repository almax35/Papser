package com.example.parser.controller;

import com.example.parser.dto.JwtRequest;
import com.example.parser.dto.JwtResponse;
import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.service.auth.AuthService;
import com.example.parser.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    public String creatAuthToken(@RequestParam String login, @RequestParam String password) throws BadCredentialsException{
        String token=authService.getToken(new JwtRequest(login,password));
        return "redirect:/table";
    }
    @PostMapping("/registration")
    public String createUser(@RequestParam String login, @RequestParam String password,@RequestParam String verifyPassword) {
        userService.save(new UserRegistrationDto(login,password,verifyPassword));
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


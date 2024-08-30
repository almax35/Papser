package com.example.parser.controller;

import com.example.parser.dto.JwtRequest;
import com.example.parser.dto.JwtResponse;
import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.service.auth.AuthService;
import com.example.parser.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<JwtResponse> creatAuthToken(@RequestBody JwtRequest request) throws BadCredentialsException{
        String token=authService.getToken(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return ResponseEntity.ok("user is created");
    }
}

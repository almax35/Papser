package com.example.parser.controller.api;

import com.example.parser.dto.JwtRequest;
import com.example.parser.dto.JwtResponse;
import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.service.auth.AuthService;
import com.example.parser.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiAuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> creatAuthToken(@Valid @RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        String token=authService.getToken(jwtRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return ResponseEntity.ok("user is created");
    }
}

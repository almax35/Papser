package com.example.parser.controller.api;

import com.example.parser.dto.JwtRequest;
import com.example.parser.dto.JwtResponse;
import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.service.auth.AuthService;
import com.example.parser.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="authController")
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiAuthController {
    private final AuthService authService;
    private final UserService userService;

    @Operation(
            summary = "auth user",
            description = "get login password and return jwtToken "
    )
    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> creatAuthToken(@Valid @RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        String token=authService.getToken(jwtRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @Operation(
            summary = "registration user",
            description = "registration user with login, password, verifyPassword and save him in database"
    )
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return ResponseEntity.ok("user is created");
    }
}

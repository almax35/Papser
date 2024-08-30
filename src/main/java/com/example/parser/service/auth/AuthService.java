package com.example.parser.service.auth;

import com.example.parser.dto.JwtRequest;
import com.example.parser.token.JwtTokenManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenManager jwtTokenManagement;

    public String getToken (JwtRequest request) throws BadCredentialsException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getLogin());
        return jwtTokenManagement.generateJwtToken(userDetails);
    }
}
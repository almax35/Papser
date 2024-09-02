package com.example.parser.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpStatus> handleException() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

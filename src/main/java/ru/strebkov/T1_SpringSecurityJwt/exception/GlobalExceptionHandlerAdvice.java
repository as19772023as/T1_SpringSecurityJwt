package ru.strebkov.T1_SpringSecurityJwt.exception;

import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {


    @ExceptionHandler(NoSuchPersonEndpointException.class)
    public ResponseEntity<String> handlerNoSuchError(NoSuchPersonEndpointException e) {
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handlerNoValidJwtToken(AuthException e) {
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
package ru.strebkov.T1_SpringSecurityJwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {


    @ExceptionHandler(NoSuchTasksEndpointException.class)
    public ResponseEntity<String> handlerNoSuchError(NoSuchTasksEndpointException e) {
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
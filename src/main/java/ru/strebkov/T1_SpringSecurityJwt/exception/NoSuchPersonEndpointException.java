package ru.strebkov.T1_SpringSecurityJwt.exception;


public class NoSuchPersonEndpointException extends  RuntimeException {


    public NoSuchPersonEndpointException(String msg) {
        super(msg);
    }
}
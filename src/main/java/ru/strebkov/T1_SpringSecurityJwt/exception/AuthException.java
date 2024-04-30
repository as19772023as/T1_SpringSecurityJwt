package ru.strebkov.T1_SpringSecurityJwt.exception;

public class AuthException extends  RuntimeException {


    public AuthException(String msg) {
        super(msg);
    }
}

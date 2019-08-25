package com.bank.exception;

public class LoginAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super("Login name already used! ");
    }
}

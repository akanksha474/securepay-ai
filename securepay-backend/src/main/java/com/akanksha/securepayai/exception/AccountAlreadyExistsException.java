package com.akanksha.securepayai.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String message) {

        super(message);
    }
}

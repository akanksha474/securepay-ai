package com.akanksha.securepayai.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}

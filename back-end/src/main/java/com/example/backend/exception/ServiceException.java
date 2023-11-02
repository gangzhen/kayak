package com.example.backend.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(String msg) {
        super(msg);
    }
}

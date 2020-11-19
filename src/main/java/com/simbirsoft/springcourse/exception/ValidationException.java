package com.simbirsoft.springcourse.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}

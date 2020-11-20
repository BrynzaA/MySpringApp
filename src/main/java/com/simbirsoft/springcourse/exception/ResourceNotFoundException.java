package com.simbirsoft.springcourse.exception;



public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String message){
        super(message);
    }

}

package com.microservice.user_service.exception.custom;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message){
        super(message);
    }
}

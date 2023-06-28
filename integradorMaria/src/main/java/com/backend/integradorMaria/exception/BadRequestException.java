package com.backend.integradorMaria.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }
}

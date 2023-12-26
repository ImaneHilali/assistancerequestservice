package com.example.assistancerequestservice.exception;

public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException(){
        super("token not valid");
    }
}
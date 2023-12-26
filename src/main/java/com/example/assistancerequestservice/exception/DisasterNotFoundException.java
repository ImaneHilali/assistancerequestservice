package com.example.assistancerequestservice.exception;

public class DisasterNotFoundException extends RuntimeException{

    public DisasterNotFoundException(){
        super("disaster not found");
    }
}
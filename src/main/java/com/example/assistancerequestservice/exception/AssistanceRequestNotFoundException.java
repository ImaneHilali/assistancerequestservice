package com.example.assistancerequestservice.exception;

public class AssistanceRequestNotFoundException extends RuntimeException {

    public AssistanceRequestNotFoundException() {
        super("Assistance request not found");
    }

    public AssistanceRequestNotFoundException(String message) {
        super(message);
    }

}

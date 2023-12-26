package com.example.assistancerequestservice.exception;

public class ZoneNotFoundException extends RuntimeException {

    public ZoneNotFoundException() {
        super("No zone found for the given localisation.");
    }
}
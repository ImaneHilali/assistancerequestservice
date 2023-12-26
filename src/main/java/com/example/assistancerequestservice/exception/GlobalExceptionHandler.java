package com.example.assistancerequestservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handelTokenNotValidException(TokenNotValidException e){
        return e.getMessage();
    }

    @ExceptionHandler(AssistanceRequestNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelAssistanceRequestException(AssistanceRequestNotFoundException e){
        return e.getMessage();
    };

    @ExceptionHandler(DisasterNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelAssistanceRequestException(DisasterNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ZoneNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelAssistanceRequestException(ZoneNotFoundException e){
        return e.getMessage();
    };

}
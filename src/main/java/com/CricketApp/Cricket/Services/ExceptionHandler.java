package com.CricketApp.Cricket.Services;

//handling exception
public class ExceptionHandler extends RuntimeException{

    public ExceptionHandler() {}

    public ExceptionHandler(String message) {
        super(message);
    }
}

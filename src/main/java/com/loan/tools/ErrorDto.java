package com.loan.tools;


public class ErrorDto{
    private String message;

    public ErrorDto(){}

    public ErrorDto(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}

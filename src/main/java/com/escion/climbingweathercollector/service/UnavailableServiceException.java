package com.escion.climbingweathercollector.service;

public class UnavailableServiceException extends Exception{

    public UnavailableServiceException(String message){
        super(message);
    }
}

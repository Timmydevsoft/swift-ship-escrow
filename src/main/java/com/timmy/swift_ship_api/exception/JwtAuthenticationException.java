package com.timmy.swift_ship_api.exception;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(String message){
        super(message);
    }
}

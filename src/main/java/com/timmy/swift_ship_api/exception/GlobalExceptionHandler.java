package com.timmy.swift_ship_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(JwtAuthenticationException.class)
    ResponseEntity<Map<String, Object>> handleJwtError(JwtAuthenticationException ex){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 401);
        res.put("message", ex.getMessage() );
        res.put("error", "Unauthorised");

        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Map<String, Object>> handleNotFoundException(ResourceNotFoundException ex){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 404);
        res.put("message", ex.getMessage() );
        res.put("error", "resource not found");

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    ResponseEntity<Map<String, Object>> handleDuplicateResourcesException(DuplicateResourceException ex){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 422);
        res.put("message", ex.getMessage() );
        res.put("error", "resource not found");

        return new ResponseEntity<>(res, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

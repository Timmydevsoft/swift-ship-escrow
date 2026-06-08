package com.timmy.swift_ship_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/system-health")
public class HealthCheckController {
    public record HealthResponse(String message, Integer status){};

    @GetMapping("/check")
    public ResponseEntity<HealthResponse> handleHealthCheck(){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 200);
        res.put("message", "Health ok");
        HealthResponse response = new HealthResponse("Health is Ok", 200);

        return ResponseEntity.ok(response) ;
    }
}

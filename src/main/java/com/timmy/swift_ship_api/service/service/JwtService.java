package com.timmy.swift_ship_api.service.service;

import com.timmy.swift_ship_api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
     String generateToken(User user);


    String extractEmail(String token);

    boolean isTokenExpired(String token);

    boolean isValid(String token, UserDetails userDetails);

}

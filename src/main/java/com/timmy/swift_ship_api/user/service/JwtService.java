package com.timmy.swift_ship_api.user.service;

import com.timmy.swift_ship_api.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
     String generateToken(User user);


    String extractEmail(String token);

    boolean isTokenExpired(String token);

    boolean isValid(String token, UserDetails userDetails);

}

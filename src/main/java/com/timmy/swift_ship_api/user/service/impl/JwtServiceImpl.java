package com.timmy.swift_ship_api.user.service.impl;

import com.timmy.swift_ship_api.user.User;
import com.timmy.swift_ship_api.user.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtDuration;

//    private Key key;

//    @PostConstruct
//    public void init() {
//        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        Long expirationTime = Instant.now().toEpochMilli() + (5*60*1000);
        claims.put("id", user.getId());
        claims.put("role", user.getRoles());
        claims.put("email", user.getEmail());
        claims.put("expiration_time", expirationTime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtDuration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Claims extractClaim(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        return extractClaim(token).getSubject();
    };

    public boolean isTokenExpired(String token){
        return extractClaim(token).getExpiration().before(new Date());
    }

    public boolean isValid(String token, UserDetails userDetails){
        final String userEmail  = extractEmail(token);
        boolean isValidEmail = userEmail.equals(userDetails.getUsername());
        return isValidEmail && !isTokenExpired(token);
    }

}

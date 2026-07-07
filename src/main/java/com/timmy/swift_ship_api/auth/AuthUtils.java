package com.timmy.swift_ship_api.auth;

import com.timmy.swift_ship_api.exception.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    public  String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth ==null || !auth.isAuthenticated() ){
            throw new AccessDeniedException("Unauthenticated");
        }
        String userId = null;
        Object principal = auth.getPrincipal();
        boolean isPrincipalAuth = principal instanceof AuthUserDetails;

        if(!isPrincipalAuth) throw new AccessDeniedException("Invalid credentials");
        userId= ((AuthUserDetails) principal).getUsername();
        return userId;
    }
}

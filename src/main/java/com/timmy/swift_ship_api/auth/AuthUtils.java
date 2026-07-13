package com.timmy.swift_ship_api.auth;

import com.timmy.swift_ship_api.exception.AccessDeniedException;
import com.timmy.swift_ship_api.user.User;
import com.timmy.swift_ship_api.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    public  String getUserId() {
        return getPrincipal().getUsername();
    }

    private AuthUserDetails getPrincipal(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth ==null || !auth.isAuthenticated() ){
            throw new AccessDeniedException("Unauthenticated");
        }

        Object principal = auth.getPrincipal();
        boolean isPrincipalAuth = principal instanceof AuthUserDetails;

        if(!isPrincipalAuth) throw new AccessDeniedException("Invalid credentials");
        return (AuthUserDetails) principal;
    }

    public User getLoggedUser(){
        return null;
    }
}

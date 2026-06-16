package com.timmy.swift_ship_api.service.impl;

import com.timmy.swift_ship_api.repo.UserRepository;
import com.timmy.swift_ship_api.entity.User;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        User user = userRepo.findUserByEmail(email).orElseThrow(()->new ResourceNotFoundException("No such user with email "+email));
//        return new CustomUserDetails(user);
        return null;
    }

}

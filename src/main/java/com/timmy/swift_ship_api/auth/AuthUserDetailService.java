package com.timmy.swift_ship_api.auth;


import com.timmy.swift_ship_api.entity.User;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import com.timmy.swift_ship_api.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userExist = userRepository.findUserByEmail(username);
        if(userExist.isEmpty()) throw new ResourceNotFoundException("user with email "+username +" not found");
        User user = userExist.get();

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(item -> new SimpleGrantedAuthority(item.name()))
                .collect(Collectors.toSet());

        return new AuthUserDetails(user.getEmail(), user.getPassword(), authorities);
    }
}

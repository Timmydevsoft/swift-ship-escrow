package com.timmy.swift_ship_api.service.impl;

import com.timmy.swift_ship_api.dto.request.CreateUserRequestDto;
import com.timmy.swift_ship_api.dto.response.CreateUserResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.entity.User;
import com.timmy.swift_ship_api.entity.enums.Roles;
import com.timmy.swift_ship_api.exception.DuplicateResourceException;
import com.timmy.swift_ship_api.repo.UserRepository;
import com.timmy.swift_ship_api.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseWrapper<CreateUserResponse> signUp(CreateUserRequestDto payload) {
        Optional<User> existingUserOptional = userRepo.findUserByEmail(payload.getEmail());
        if(existingUserOptional.isPresent()) throw new DuplicateResourceException("Email already taken");
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.MERCHANT);
        User newUser = User.builder()
                .email(payload.getEmail())
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .password(passwordEncoder.encode(payload.getPassword()))
                .roles(roles)
                .build();

        User savedUser = userRepo.save(newUser);
        var data = CreateUserResponse.builder()
                .email(savedUser.getEmail())
                .id(savedUser.getId())
                .lastName(savedUser.getLastName())
                .firstName(savedUser.getFirstName())
                .build();

            return ResponseWrapper.<CreateUserResponse>builder()
                .data(data)
                .message("Account created successfully")
                .httpStatusCode(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
                .build();

    }
}

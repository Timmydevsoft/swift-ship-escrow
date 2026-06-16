package com.timmy.swift_ship_api.service.impl;

import com.timmy.swift_ship_api.dto.proxy.LoginSessionProxy;
import com.timmy.swift_ship_api.dto.request.CreateUserRequestDto;
import com.timmy.swift_ship_api.dto.request.LoginRequest;
import com.timmy.swift_ship_api.dto.response.CreateUserResponse;
import com.timmy.swift_ship_api.dto.response.LoginResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.entity.User;
import com.timmy.swift_ship_api.entity.enums.Roles;
import com.timmy.swift_ship_api.exception.AccessDeniedException;
import com.timmy.swift_ship_api.exception.DuplicateResourceException;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import com.timmy.swift_ship_api.repo.UserRepository;
import com.timmy.swift_ship_api.service.service.JwtService;
import com.timmy.swift_ship_api.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RedisTemplate<String, Object> redisTemplate;

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

    @Override
    public ResponseWrapper<LoginResponse> login(LoginRequest payload){
        Optional<User> optionalUser = userRepo.findUserByEmail(payload.getEmail());
        if(optionalUser.isEmpty()) throw new ResourceNotFoundException("Incorrect email");
        User user = optionalUser.get();
        return getAuthResponse(payload, user);
    }

    private ResponseWrapper<LoginResponse> getAuthResponse(LoginRequest payload, User user){
        try{
            log.error("fired services");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));
            //
            String x_id_key = UUID.randomUUID().toString();
            String token = jwtService.generateToken(user);
            LoginSessionProxy session = LoginSessionProxy.builder()
                    .token(token)
                    .key(x_id_key)
                    .build();
            redisTemplate.opsForValue().set(x_id_key, session);
            LoginResponse response = new LoginResponse(x_id_key, user.getId(), user.getFirstName(), user.getLastName());
            return ResponseWrapper.<LoginResponse>builder()
                    .data(response)
                    .httpStatusCode(HttpStatus.OK)
                    .message("Login successfull")
                    .build();
        }
        catch(BadCredentialsException ex){
            log.error("Error login in"+ex.getMessage(), ex.toString());
            throw new AccessDeniedException(ex.getMessage());
        }
    }
}

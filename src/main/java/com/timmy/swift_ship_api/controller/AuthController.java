package com.timmy.swift_ship_api.controller;

import com.timmy.swift_ship_api.dto.request.CreateUserRequestDto;
import com.timmy.swift_ship_api.dto.request.LoginRequest;
import com.timmy.swift_ship_api.dto.response.CreateUserResponse;
import com.timmy.swift_ship_api.dto.response.LoginResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseWrapper<CreateUserResponse> signup(@RequestBody @Valid CreateUserRequestDto payload){
        return userService.signUp(payload);
    }

//    @PostMapping("/login")
//    public ResponseWrapper<LoginResponse>login(@RequestBody @Valid LoginRequest payload){
//        return userService.login(payload);
//    }

    @PostMapping("/login")
    public ResponseWrapper<LoginResponse> login(@RequestBody @Valid LoginRequest payload){
        return userService.login(payload);
//        return "login successful";
    }
}

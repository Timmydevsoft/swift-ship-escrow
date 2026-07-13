package com.timmy.swift_ship_api.user.service;

import com.timmy.swift_ship_api.dto.request.CreateUserRequestDto;
import com.timmy.swift_ship_api.dto.request.LoginRequest;
import com.timmy.swift_ship_api.dto.response.CreateUserResponse;
import com.timmy.swift_ship_api.dto.response.LoginResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.user.User;

public interface UserService {
    ResponseWrapper<CreateUserResponse> signUp(CreateUserRequestDto payload);

    ResponseWrapper<LoginResponse> login(LoginRequest payload);

    User getLoggedInUser();

    User getUserBySwiftId(String swiftId);
}

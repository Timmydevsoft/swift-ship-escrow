package com.timmy.swift_ship_api.service.service;

import com.timmy.swift_ship_api.dto.request.CreateUserRequestDto;
import com.timmy.swift_ship_api.dto.response.CreateUserResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;

public interface UserService {
    ResponseWrapper<CreateUserResponse> signUp(CreateUserRequestDto payload);
}

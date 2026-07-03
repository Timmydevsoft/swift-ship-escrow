package com.timmy.swift_ship_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class CreateUserRequestDto {
    @NotBlank(message="password is required")
    private String password;
    @NotBlank(message="email is required")
    private String email;
}

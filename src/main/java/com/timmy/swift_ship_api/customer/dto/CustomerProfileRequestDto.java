package com.timmy.swift_ship_api.customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerProfileRequestDto {
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message="lastName is required")
    private String lastName;
    @NotBlank(message = "avatar is required")
    private String avatar;
}

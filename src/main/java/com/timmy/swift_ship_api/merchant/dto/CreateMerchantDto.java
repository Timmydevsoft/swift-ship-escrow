package com.timmy.swift_ship_api.merchant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateMerchantDto {
    @NotBlank(message = "businessName is required" )
    private String businessName;

    @NotBlank(message = "bio must not be empty")
    private String bio;

    @NotBlank(message = "avatar is required")
    private String avatar;
}

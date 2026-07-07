package com.timmy.swift_ship_api.dto.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginSessionProxy {
    private String token;
//    private Long expirationTime;
    private String key;
}

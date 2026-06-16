package com.timmy.swift_ship_api.dto.proxy;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginSessionProxy {
    private String token;
//    private Long expirationTime;
    private String key;
}

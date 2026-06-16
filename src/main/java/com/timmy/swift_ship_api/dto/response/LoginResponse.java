package com.timmy.swift_ship_api.dto.response;


import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    private String x_id_key;
    private UUID user_id;
    private String firstName;
    private String lastName;
}

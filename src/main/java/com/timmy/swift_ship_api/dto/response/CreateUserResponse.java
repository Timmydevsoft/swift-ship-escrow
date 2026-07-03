package com.timmy.swift_ship_api.dto.response;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private String email;
    private UUID id;
}

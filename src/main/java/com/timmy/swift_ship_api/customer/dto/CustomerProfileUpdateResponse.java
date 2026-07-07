package com.timmy.swift_ship_api.customer.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerProfileUpdateResponse {
    private UUID id;
    private BigDecimal balance;
    private String firstName;
    private String lastName;
    private String avatar;
}

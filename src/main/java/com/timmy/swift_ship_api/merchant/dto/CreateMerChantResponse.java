package com.timmy.swift_ship_api.merchant.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateMerChantResponse {
    private String avatar;
    private String businessName;
    private UUID id;
    private BigDecimal balance;
    private String bio;
}

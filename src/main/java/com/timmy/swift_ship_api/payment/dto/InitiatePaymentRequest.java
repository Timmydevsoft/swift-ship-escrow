package com.timmy.swift_ship_api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InitiatePaymentRequest {
    private String email;
    private Long amount;
    private String reference;
}

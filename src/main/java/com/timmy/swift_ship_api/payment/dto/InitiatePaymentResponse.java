package com.timmy.swift_ship_api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InitiatePaymentResponse {
    private boolean status;
    private String message;
    private PaystackInitializationData data;
}

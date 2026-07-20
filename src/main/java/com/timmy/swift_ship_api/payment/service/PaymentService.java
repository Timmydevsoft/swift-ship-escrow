package com.timmy.swift_ship_api.payment.service;

import com.timmy.swift_ship_api.payment.dto.InitiatePaymentRequest;
import com.timmy.swift_ship_api.payment.dto.InitiatePaymentResponse;

import java.util.UUID;

public interface PaymentService {
    InitiatePaymentResponse initiatePayment(UUID escrowId);
}

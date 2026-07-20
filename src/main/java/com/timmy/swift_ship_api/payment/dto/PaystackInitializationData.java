package com.timmy.swift_ship_api.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaystackInitializationData {
        private String authorization_url;
        private String access_code;
        private String reference;
    }

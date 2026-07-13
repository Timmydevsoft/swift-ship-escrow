package com.timmy.swift_ship_api.customer.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummary {

    private UUID customerId;

    private String firstName;

    private String lastName;

    private String avatar;
}
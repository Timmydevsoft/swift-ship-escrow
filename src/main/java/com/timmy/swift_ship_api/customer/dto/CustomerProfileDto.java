package com.timmy.swift_ship_api.customer.dto;

import org.apache.poi.hpsf.Decimal;

import java.util.UUID;

public class CustomerProfileDto {
    private UUID id;
    private Decimal balance;
    private String lastName;
    private String firstName;
    private String avatar;
}

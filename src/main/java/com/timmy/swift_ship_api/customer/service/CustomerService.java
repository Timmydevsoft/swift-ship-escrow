package com.timmy.swift_ship_api.customer.service;

import com.timmy.swift_ship_api.customer.dto.CustomerProfileRequestDto;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileUpdateResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;

public interface CustomerService {
   ResponseWrapper< CustomerProfileUpdateResponse> createCustomerProfile(CustomerProfileRequestDto dto);
}

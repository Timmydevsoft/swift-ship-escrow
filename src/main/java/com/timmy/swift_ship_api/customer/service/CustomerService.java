package com.timmy.swift_ship_api.customer.service;

import com.timmy.swift_ship_api.customer.Customer;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileRequestDto;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileUpdateResponse;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.user.User;

public interface CustomerService {
   ResponseWrapper< CustomerProfileUpdateResponse> createCustomerProfile(CustomerProfileRequestDto dto);
   Customer getCustomerByUser(User user);

}

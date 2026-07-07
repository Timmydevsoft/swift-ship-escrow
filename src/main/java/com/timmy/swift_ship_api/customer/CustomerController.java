package com.timmy.swift_ship_api.customer;

import com.timmy.swift_ship_api.customer.dto.CustomerProfileRequestDto;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileUpdateResponse;
import com.timmy.swift_ship_api.customer.service.CustomerService;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseWrapper<CustomerProfileUpdateResponse> createCustomer(@Valid @RequestBody CustomerProfileRequestDto dto){
        return customerService.createCustomerProfile(dto);
    }

}

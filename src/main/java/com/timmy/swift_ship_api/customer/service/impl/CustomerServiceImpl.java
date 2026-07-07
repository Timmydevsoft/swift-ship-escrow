package com.timmy.swift_ship_api.customer.service.impl;

import com.timmy.swift_ship_api.auth.AuthUserDetails;
import com.timmy.swift_ship_api.auth.AuthUtils;
import com.timmy.swift_ship_api.customer.Customer;
import com.timmy.swift_ship_api.customer.CustomerRepository;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileRequestDto;
import com.timmy.swift_ship_api.customer.dto.CustomerProfileUpdateResponse;
import com.timmy.swift_ship_api.customer.service.CustomerService;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.exception.AccessDeniedException;
import com.timmy.swift_ship_api.exception.DuplicateResourceException;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import com.timmy.swift_ship_api.user.User;
import com.timmy.swift_ship_api.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Setter
@Getter
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final AuthUtils authUtils;


    @Override
    public ResponseWrapper<CustomerProfileUpdateResponse> createCustomerProfile(CustomerProfileRequestDto dto) {
        String userName = authUtils.getUserId();
        Optional<User> isAUser = userRepo.findUserByEmail(userName);
       if(isAUser.isEmpty()) throw new ResourceNotFoundException("user not found");

       User user = isAUser.get();

       Optional<Customer> isCustomer = customerRepo.findCustomerByUser(user);

       if(isCustomer.isPresent()) throw new DuplicateResourceException("Already a customer");


        Customer newCustomer = Customer.builder()
                .avatar(dto.getAvatar())
                .firstName(dto.getFirstName())
                .user(user)
                .lastName(dto.getLastName())
                .avatar(dto.getAvatar())
                .build();

        var savedCustomer = customerRepo.save(newCustomer);
        return ResponseWrapper.<CustomerProfileUpdateResponse>builder()
                .data(CustomerProfileUpdateResponse.builder()
                        .id(savedCustomer.getId())
                        .firstName(savedCustomer.getFirstName())
                        .lastName(savedCustomer.getLastName())
                        .avatar(savedCustomer.getAvatar())
                        .balance(savedCustomer.getBalance())
                        .build())
                .message("Customer profile updated successfully")
                .httpStatusCode(HttpStatusCode.valueOf(HttpStatus.OK.value()))
                .build();
    }


}

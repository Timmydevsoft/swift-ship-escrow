package com.timmy.swift_ship_api.escrow.service.impl;

import com.timmy.swift_ship_api.auth.AuthUtils;
import com.timmy.swift_ship_api.customer.Customer;
import com.timmy.swift_ship_api.customer.dto.CustomerSummary;
import com.timmy.swift_ship_api.customer.service.CustomerService;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.escrow.Escrow;
import com.timmy.swift_ship_api.escrow.EscrowRepository;
import com.timmy.swift_ship_api.escrow.EscrowStatus;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowDto;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowResponse;
import com.timmy.swift_ship_api.escrow.service.EscrowService;
import com.timmy.swift_ship_api.exception.BadRequestException;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import com.timmy.swift_ship_api.merchant.Merchant;
import com.timmy.swift_ship_api.merchant.MerchantRepository;
import com.timmy.swift_ship_api.merchant.service.MerchantService;
import com.timmy.swift_ship_api.user.User;
import com.timmy.swift_ship_api.user.UserRepository;
import com.timmy.swift_ship_api.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EscrowServiceImpl implements EscrowService {
    private final MerchantService merchantService;
    private final CustomerService customerService;
    private final UserService userService;
    private final EscrowRepository escrowRepo;
    private final AuthUtils authUtils;


    @Override
    @Transactional
    public ResponseWrapper<CreateEscrowResponse> initiateEscrow(CreateEscrowDto dto) {
        Customer customer;
        Merchant merchant;

        User loggedInUser = userService.getLoggedInUser();// The user initiating the escrow
        Optional<Merchant> isMerchant = merchantService.getMerchantByUser(loggedInUser);

        if(isMerchant.isEmpty()) throw new ResourceNotFoundException("You have no merchant profile to initiate Escrow");
        merchant = isMerchant.get();

        User targetCustomerUser = userService.getUserBySwiftId(dto.getTargetUserSwiftId());
        customer = customerService.getCustomerByUser(targetCustomerUser);

        if (loggedInUser.getId().equals(targetCustomerUser.getId())) {
            throw new BadRequestException("You cannot initiate an escrow with yourself.");
        }

        Escrow newEscrow = Escrow.builder()
                .price(dto.getPrice())
                .description(dto.getDescription())
                .status(EscrowStatus.PENDING_ACCEPTANCE)
                .itemName(dto.getItemName())
                .customer(customer)
                .merchant(merchant)
                .build();

        Escrow savedEscrow = escrowRepo.save(newEscrow);
        var resData = getCreateEscrowResponse(savedEscrow, customer);

        return ResponseWrapper.<CreateEscrowResponse>builder()
                .data(resData)
                .message("Escrow created successfully")
                .httpStatusCode(HttpStatus.CREATED)
                .build();

    }

    @Override
    public ResponseWrapper<CreateEscrowResponse> updateEscrowInitiation(UUID escrowId) {

        Optional<Escrow> optionalEscrow = escrowRepo.findById(escrowId);
        if(optionalEscrow.isEmpty())throw new ResourceNotFoundException("Escrow not found");
        User loggedInUser = authUtils.getLoggedUser();
        Escrow targetEscrow = optionalEscrow.get();

        if (targetEscrow.getStatus() != EscrowStatus.PENDING_ACCEPTANCE) {
            throw new BadRequestException("This escrow has already been responded to.");
        }

        Customer loggedInCustomer = customerService.getCustomerByUser(loggedInUser);

        if(!loggedInCustomer.getId().equals(targetEscrow.getCustomer().getId()))throw new BadRequestException("You are not authorized to perform the action");
        targetEscrow.setStatus(EscrowStatus.ACCEPTED);
        var updatedEscrow =escrowRepo.save(targetEscrow);

        var resData = getCreateEscrowResponse(updatedEscrow, loggedInCustomer);
        return ResponseWrapper.<CreateEscrowResponse>builder()
                .data(resData)
                .message("Escrow accepted successfully")
                .httpStatusCode(HttpStatus.OK)
                .build();
    }

    private CustomerSummary getCustomerSummary(Customer customer){
        return CustomerSummary
                .builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .avatar(customer.getAvatar())
                .customerId(customer.getId())
                .build();
    }

    private CreateEscrowResponse getCreateEscrowResponse(Escrow escrow, Customer customer){
        return CreateEscrowResponse.builder()
                .price(escrow.getPrice()).
                description(escrow.getDescription()).
                customer(getCustomerSummary(customer))
                .escrowStatus(escrow.getStatus())
                .itemName(escrow.getItemName())
                .build();
    }
}

package com.timmy.swift_ship_api.merchant.service.impl;

import com.timmy.swift_ship_api.auth.AuthUtils;
import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.exception.AccessDeniedException;
import com.timmy.swift_ship_api.exception.DuplicateResourceException;
import com.timmy.swift_ship_api.exception.ResourceNotFoundException;
import com.timmy.swift_ship_api.merchant.Merchant;
import com.timmy.swift_ship_api.merchant.MerchantRepository;
import com.timmy.swift_ship_api.merchant.dto.CreateMerChantResponse;
import com.timmy.swift_ship_api.merchant.dto.CreateMerchantDto;
import com.timmy.swift_ship_api.merchant.service.MerchantService;
import com.timmy.swift_ship_api.user.User;
import com.timmy.swift_ship_api.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
@Getter
@Setter
public class MerchantServiceImpl implements MerchantService {
    private final AuthUtils authUtils;
    private final UserRepository userRepo;
    private final MerchantRepository merchantRepo;


    @Override
    public ResponseWrapper<CreateMerChantResponse> createMerchant(CreateMerchantDto dto) {
        String userName = authUtils.getUserId();

        if(userName ==null) throw new AccessDeniedException("Unauthorized user");

        Optional<User> isAUser = userRepo.findUserByEmail(userName);

        if(!isAUser.isPresent()) throw new ResourceNotFoundException("User not found, kindly sign up");

        User user = isAUser.get();

        Optional<Merchant> isAMerchant = merchantRepo.findMerchantByUser(user);

        if(isAMerchant.isPresent()) throw new DuplicateResourceException("Already a merchant");

        Merchant newMerchant = Merchant.builder()
                .businessName(dto.getBusinessName())
                .bio(dto.getBio())
                .user(user)
                .avatar(dto.getAvatar())
                .balance(BigDecimal.valueOf(0))
                .build();

        Merchant savedMerchant = merchantRepo.save(newMerchant);





        return ResponseWrapper.<CreateMerChantResponse>builder()
                .data(CreateMerChantResponse.builder()
                        .id(savedMerchant.getId())
                        .bio(savedMerchant.getBio())
                        .businessName(savedMerchant.getBusinessName())
                        .avatar(savedMerchant.getAvatar())
                        .balance(savedMerchant.getBalance())
                        .build())
                .message("Merchant profile creation successful")
                .httpStatusCode(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
                .build();
    }

    @Override
    public Optional<Merchant> getMerchantByUser(User user) {
        return merchantRepo.findMerchantByUser(user);
    }

}

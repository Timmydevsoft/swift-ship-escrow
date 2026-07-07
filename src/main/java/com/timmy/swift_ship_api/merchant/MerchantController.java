package com.timmy.swift_ship_api.merchant;

import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.merchant.dto.CreateMerChantResponse;
import com.timmy.swift_ship_api.merchant.dto.CreateMerchantDto;
import com.timmy.swift_ship_api.merchant.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;


    @PostMapping("/create")
    public ResponseWrapper<CreateMerChantResponse> createMerchant(@Valid @RequestBody CreateMerchantDto dto){
        return merchantService.createMerchant(dto);
    }
}

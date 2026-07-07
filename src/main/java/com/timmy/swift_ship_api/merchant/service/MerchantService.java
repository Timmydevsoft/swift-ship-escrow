package com.timmy.swift_ship_api.merchant.service;

import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.merchant.dto.CreateMerChantResponse;
import com.timmy.swift_ship_api.merchant.dto.CreateMerchantDto;

public interface MerchantService {
    ResponseWrapper<CreateMerChantResponse> createMerchant(CreateMerchantDto dto);
}

package com.timmy.swift_ship_api.escrow.service;

import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowDto;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowResponse;

public interface EscrowService {
    ResponseWrapper<CreateEscrowResponse> initiateEscrow(CreateEscrowDto dto);
}

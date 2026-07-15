package com.timmy.swift_ship_api.escrow.service;

import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.escrow.EscrowStatus;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowDto;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowResponse;

import java.util.UUID;

public interface EscrowService {
    ResponseWrapper<CreateEscrowResponse> initiateEscrow(CreateEscrowDto dto);
    ResponseWrapper<CreateEscrowResponse> updateEscrowInitiation(UUID escrowId);
}

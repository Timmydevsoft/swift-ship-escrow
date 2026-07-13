package com.timmy.swift_ship_api.escrow;

import com.timmy.swift_ship_api.dto.response.ResponseWrapper;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowDto;
import com.timmy.swift_ship_api.escrow.dto.CreateEscrowResponse;
import com.timmy.swift_ship_api.escrow.service.EscrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/escrow")
@RequiredArgsConstructor
public class EscrowController {
    private final EscrowService escrowService;

    @PostMapping("initiate")
    public ResponseWrapper<CreateEscrowResponse> initiateEscrow(@RequestBody CreateEscrowDto dto){
        return escrowService.initiateEscrow(dto);
    }
}

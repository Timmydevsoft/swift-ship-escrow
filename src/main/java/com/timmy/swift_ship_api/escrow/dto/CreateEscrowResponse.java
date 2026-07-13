package com.timmy.swift_ship_api.escrow.dto;
import com.timmy.swift_ship_api.customer.dto.CustomerSummary;
import com.timmy.swift_ship_api.escrow.EscrowStatus;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateEscrowResponse {
    private BigDecimal price;

    private String description;

    private CustomerSummary customer;

    private EscrowStatus escrowStatus;

    private String itemName;
}

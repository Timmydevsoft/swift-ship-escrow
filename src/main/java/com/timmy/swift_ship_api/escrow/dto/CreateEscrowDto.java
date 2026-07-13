package com.timmy.swift_ship_api.escrow.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CreateEscrowDto {
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotBlank(message = "Item name is required")
    private String targetUserSwiftId;
}

package com.timmy.swift_ship_api.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rate_cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RateCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromLga;
    private String toLga;

    private Double basePrice;

    private Double pricePerKg;
}

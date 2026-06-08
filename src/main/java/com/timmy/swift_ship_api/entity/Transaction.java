package com.timmy.swift_ship_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    private String paystackReference;

    private Double amount;

    private String status;

    private LocalDateTime createdAt;

    @OneToOne
    private Shipment shipment;
}

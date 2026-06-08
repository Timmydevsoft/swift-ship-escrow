package com.timmy.swift_ship_api.entity;
import com.timmy.swift_ship_api.entity.enums.PaymentStatus;
import com.timmy.swift_ship_api.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Shipment {
    @Id
    @GeneratedValue
    private UUID id;

    private String trackingId;

    private String senderName;
    private String senderPhone;

    private String receiverName;
    private String receiverPhone;

    private String pickupAddress;
    private String deliveryAddress;

    private Double weight;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String assignedCourier;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

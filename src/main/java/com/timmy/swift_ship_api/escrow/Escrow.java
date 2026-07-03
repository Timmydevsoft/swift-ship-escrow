package com.timmy.swift_ship_api.escrow;

import com.timmy.swift_ship_api.customer.Customer;
import com.timmy.swift_ship_api.enums.EscrowPaymentStatus;
import com.timmy.swift_ship_api.enums.EscrowStatus;
import com.timmy.swift_ship_api.merchant.Merchant;
import com.timmy.swift_ship_api.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "escrows")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Escrow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private BigDecimal price;

    @Column
    private String description;

    @Column(name="item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column
    @Enumerated(EnumType.STRING)
    private EscrowStatus status;

    @OneToMany(mappedBy = "escrow")
    private List<Transaction> transactions;

}

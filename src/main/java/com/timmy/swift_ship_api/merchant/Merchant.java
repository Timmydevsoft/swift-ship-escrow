package com.timmy.swift_ship_api.merchant;

import com.timmy.swift_ship_api.escrow.Escrow;
import com.timmy.swift_ship_api.transaction.Transaction;
import com.timmy.swift_ship_api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

    @Entity
    @Table(name = "merchants")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String avatar;

    @Column
    private String businessName;

    @Column
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "merchant")
    private List<Escrow> escrows;

    @OneToMany(mappedBy = "merchant" )
    private List<Transaction> transactions;

    @Column
    private BigDecimal balance;
}

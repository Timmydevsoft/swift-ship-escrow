package com.timmy.swift_ship_api.customer;

import com.timmy.swift_ship_api.escrow.Escrow;
import com.timmy.swift_ship_api.transaction.Transaction;
import com.timmy.swift_ship_api.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;;

    private String firstName;

    private String lastName;

    private String avatar;

    @OneToOne
    @JoinColumn(name="user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "customer")
    private List<Escrow> escrows;

    private BigDecimal balance;
}

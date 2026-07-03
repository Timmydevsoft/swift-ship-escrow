package com.timmy.swift_ship_api.escrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EscrowRepository extends JpaRepository<Escrow, UUID> {
}

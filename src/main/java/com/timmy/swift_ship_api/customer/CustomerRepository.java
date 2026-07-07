package com.timmy.swift_ship_api.customer;

import com.timmy.swift_ship_api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findCustomerByUser(User user);
}

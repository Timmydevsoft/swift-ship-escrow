package com.timmy.swift_ship_api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);

//    List<User> findBySwiftIdIsNull();
//
//    List<User> findByIsActiveIsNull();
    Optional<User> findUserBySwiftId(String swiftId);

    boolean existsBySwiftId(String swiftId);
}

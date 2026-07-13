//package com.timmy.swift_ship_api.util;
//import com.timmy.swift_ship_api.user.User;
//import com.timmy.swift_ship_api.user.UserRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class SwiftIdInitializer {
//
//    private final UserRepository userRepo;
//
//    @EventListener(ApplicationReadyEvent.class)
//    @Transactional
//    public void backfillSwiftIds() {
//        List<User> users = userRepo.findByIsActiveIsNull();
//
//        for (User user : users) {
////            String swiftId;
////
////            do {
////                swiftId = generateIdentifier();
////            } while (userRepo.existsBySwiftId(swiftId));
//
//            user.setIsActive(true);
//        }
//
//        userRepo.saveAll(users);
//    }
//
//    private String generateIdentifier() {
//        Random random = new Random();
//        int randomNum = 1_000_000 + random.nextInt(9_000_000);
//        return "SW" + randomNum;
//    }
//}

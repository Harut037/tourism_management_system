//package com.example.tourism_management_system.service.impl;
//
//import com.example.tourism_management_system.model.pojos.User;
//import com.example.tourism_management_system.service.UserService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Override
//    public void signUp(User user) {
//
//        if (user == null) {
//            throw new IllegalArgumentException("Please fill something.");
//        }
//        if (userRepository.existsById(user.getId())) {
//            throw new IllegalStateException("User already exists.");
//        }
//        userRepository.save(user);
//        sendConfirmationEmail(user);
//    }
//}

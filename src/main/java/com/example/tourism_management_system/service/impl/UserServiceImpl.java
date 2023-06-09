package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public void signUp(UserEntity userEntity) {

        if (userEntity == null) {
            throw new IllegalArgumentException("Please fill something.");
        }
        if (userRepository.existsById(userEntity.getId())) {
            throw new IllegalStateException("User already exists.");
        }
        userRepository.save(userEntity);
        sendConfirmationEmail(userEntity);
    }
}

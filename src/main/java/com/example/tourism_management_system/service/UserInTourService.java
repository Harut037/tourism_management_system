package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInTourService {
    ResponseEntity<UserInTour> save(UserInTour userInTour);
    
    List<UserInTour> findByUserId (Long userId);
}
package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserInTourServiceImpl implements UserInTourService {
    
    @Override
    public ResponseEntity <UserInTour> save (UserInTour userInTour) {
        return null;
    }
}

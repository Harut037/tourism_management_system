package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.UserInTourRepository;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserInTourServiceImpl implements UserInTourService {
    
    private final UserInTourRepository userInTourRepository;
    
    @Autowired
    public UserInTourServiceImpl(UserInTourRepository userInTourRepository) {
        this.userInTourRepository = userInTourRepository;
    }
    
    @Override
    public ResponseEntity <UserInTour> save (UserInTour userInTour) {
        return null;
    }
    
    @Override
    public List <UserInTour> findByUserId (Long userId) {
        List<UserInTour> userInTours = new ArrayList <>();
        List<UserInTourEntity> userInTourEntities = userInTourRepository.findByUserId(userId);
        if (userInTourEntities == null) {
            return Collections.emptyList();
        }for (UserInTourEntity userInTourEntity : userInTourEntities){
            userInTours.add(new UserInTour(userInTourEntity));
        }
        return userInTours;
    }
}

package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.UserInTourRepository;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserInTourServiceImpl implements UserInTourService {
    
    private final UserInTourRepository userInTourRepository;
    private final TourService          tourService;
    
    @Autowired
    public UserInTourServiceImpl(UserInTourRepository userInTourRepository, TourService tourService) {
        this.userInTourRepository = userInTourRepository;
        this.tourService = tourService;
    }
    
    @Override
    public String save (UserInTour userInTour) {
        UserInTourEntity userInTourEntity = userInTourRepository.save(new UserInTourEntity(userInTour));
        if (userInTourEntity.getId() != null) {
            return "Successfully";
        }
        return "Error";
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
    @Override
    public String cancel (UserInTour userInTour) {
        String result = userInTourRepository.cancel(userInTour.getTransactionNumber());
        if (result.equals("Successfully canceled")){
            return tourService.update(userInTour);
        }
        return "Not Successfully";
    }
    
    @Override
    public UserInTour getUserInTour (Tour tour, String email) {
        return null;
    }
}
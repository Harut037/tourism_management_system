package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.service.TourAdministratorService;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourAdministratorServiceImpl implements TourAdministratorService {
    
    private final TourService       tourService;
    private final UserInTourService userInTourService;
    
    @Autowired
    public TourAdministratorServiceImpl(TourService tourService, UserInTourService userInTourService) {
        this.tourService = tourService;
        this.userInTourService = userInTourService;
    }
    
    @Override
    public String addTour (Tour tour) {
        return tourService.save(tour);
    }
    
    @Override
    public String editTour (Tour tour) {
        return tourService.update(tour);
    }
    
    @Override
    public String removeTour (Tour tour) {
        return tourService.deleteById(tourService.getId(tour));
    }
    
    @Override
    public List <UserInTour> getAllUserInToursOfTour (Tour tour) {
        return userInTourService.findByTour(tour);
    }
}
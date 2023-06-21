package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.AdminService;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    
    private final TourService tourService;
    
    @Autowired
    public AdminServiceImpl(TourService tourService) {
        this.tourService = tourService;
    }
    
    @Override
    public String addTour (Tour tour) {
        return tourService.save(tour);
    }
    
    @Override
    public String editTour (Tour tour) {
        return tourService.updateTour(tour);
    }
    
    @Override
    public String removeTour (Tour tour) {
        return tourService.removeTour(tour);
    }
}
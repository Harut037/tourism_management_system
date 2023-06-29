package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.Tour;
import org.springframework.stereotype.Service;

@Service
public interface TourAdministratorService {
    
    String addTour (Tour tour);
    
    String editTour (Tour tour);
    
    String removeTour (Tour tour);
}
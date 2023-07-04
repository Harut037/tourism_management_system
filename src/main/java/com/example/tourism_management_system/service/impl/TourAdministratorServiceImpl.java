package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.TourAdministratorService;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourAdministratorServiceImpl implements TourAdministratorService {
    
    private final TourService tourService;
    
    @Autowired
    public TourAdministratorServiceImpl(TourService tourService) {
        this.tourService = tourService;
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
    public List<Tour> getAll() {
      return  tourService.getAll();
    }
}
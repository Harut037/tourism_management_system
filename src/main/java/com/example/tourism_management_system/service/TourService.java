package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TourService {

    String save(Tour tour);

    List<Tour> getAll();

    List<TourEntity> getAllForSchedule();

    Tour getById(Long id);

    String deleteById(Long id);

    List<Tour> sortByCost();

    List<Tour> sortByDate();

    List<Tour> sortByDistance();

    List<Tour> sortByQuantity();
    
    String update (Tour tour,Integer quantity);
    
    String update (Tour tour);

    String updateStartTime(LocalTime newStartTime, String tourName, LocalDate tourDate);

    String updateCost(double newCost,String tourName, LocalDate tourDate );

    Long getId (Tour tour);
    
    TourEntity getTour (Tour tour);

//    String updateMaxQuantity(Integer maxQuantity,String tourName, LocalDate tourDate);
}
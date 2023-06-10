package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {

    String save(Tour tour);

    List<TourEntity> getAll();

    Optional<TourEntity> getById(Long id);

    String deleteById(Long id);

    List<TourEntity> sortByCost();

    List<TourEntity> sortByDate();

    List<TourEntity> sortByQuantity();

}
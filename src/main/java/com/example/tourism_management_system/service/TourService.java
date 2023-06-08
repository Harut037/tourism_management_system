package com.example.tourism_management_system.service;


import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {

    public String save(Tour tour);
    public List<TourEntity> getAll();

    public Optional<TourEntity> getById(Long id);

    public String deleteById(Long id);

    public List<TourEntity> sortByCost();

    public List<TourEntity> sortByDate();

    public List<TourEntity> sortByQuantity();

}

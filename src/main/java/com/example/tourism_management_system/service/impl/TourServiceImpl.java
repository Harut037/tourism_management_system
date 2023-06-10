package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public String save(Tour tour) {
        TourEntity tourEntity = new TourEntity(tour);
        tourRepository.save(tourEntity);
        return "The tour has been successfully created";
    }


    @Override
    public List<TourEntity> getAll() {
        return tourRepository.findAll();
    }


    @Override
    public Optional<TourEntity> getById(Long id) {
        if (tourRepository.findById(id).isPresent()) {
            return tourRepository.findById(id);
        } else return Optional.empty();
    }

    @Override
    public String deleteById(Long id) {
        if (tourRepository.findById(id).isPresent()) {
//            tourRepository.deleteById(id);
            tourRepository.flag(id);
            return "Successfully has been deleted";
        } else return "This id does not exist.";
    }

    @Override
    public List<TourEntity> sortByCost(){
       return tourRepository.findAllOrderByCost();
    }

    @Override
    public List<TourEntity> sortByDate() {
        return tourRepository.findAllOrderByTourDate();
    }

    @Override
    public List<TourEntity> sortByQuantity() {
        return tourRepository.findAllOrderByQuantity();
    }


}

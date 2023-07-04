package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.enumForTour.PlacesForAdventure;
import com.example.tourism_management_system.model.enums.enumForTour.PlacesForCampaign;
import com.example.tourism_management_system.model.enums.enumForTour.PlacesForCultural;
import com.example.tourism_management_system.model.enums.enumForTour.TourType;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    /**
     * Overrides the default save behavior to save Tour objects.
     *
     * @param tour the Tour objects to be saved
     */
    @Override
    public String save(Tour tour) {
        TourEntity tourEntity = new TourEntity(tour);
        tourRepository.save(tourEntity);
        return "The tour has been successfully created";
    }

    /**
     * Overrides the default getAll behavior to retrieve all TourEntity objects.
     *
     * @return a list of all TourEntity objects
     */
    @Override
    public List<Tour> getAll() {
        List<Tour> list = new ArrayList<>();
        List<TourEntity> listEntity = tourRepository.findAll();
        for (TourEntity i : listEntity) {
            list.add(new Tour(i));
        }
        return list;
    }

    @Override
    public List<TourEntity> getAllForSchedule() {
        return tourRepository.findAll();
    }

    /**
     * Overrides the default getById behavior to retrieve a TourEntity object by its id.
     *
     * @param id the ID of the tour to be retrieved
     * @return an Optional containing the TourEntity object if found, or an empty Optional if not found
     */
    @Override
    public Tour getById(Long id) {
        Optional<TourEntity> op = tourRepository.findById(id);
        return op.map(Tour::new).orElse(null);
    }
    
    /**
     * Overrides the default deleteById behavior to delete a TourEntity object by its ID.
     *
     * @param id the ID of the tour to be deleted
     * @return a string indicating the success of the deletion operation
     */
    @Override
    public String deleteById(Long id) {
        if (tourRepository.findById(id).isPresent()) {
            tourRepository.flag(id);
            return "Successfully has been deleted";
        } else return "This id does not exist.";
    }
    
    /**
     * Overrides the default sortByCost behavior to retrieve all TourEntity objects sorted by cost.
     *
     * @return a list of TourEntity objects sorted by cost
     */
    @Override
    public List<Tour> sortByCost() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByCost();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }
    
    /**
     * Overrides the default sortByDate behavior to retrieve all TourEntity objects sorted by tour date.
     *
     * @return a list of TourEntity objects sorted by tour date
     */
    @Override
    public List<Tour> sortByDate() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByTourDate();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }

    @Override
    public List<Tour> sortByDistance() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByDistance();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }
    
    /**
     * Overrides the default sortByQuantity behavior to retrieve all TourEntity objects sorted by quantity.
     *
     * @return a list of TourEntity objects sorted by quantity
     */
    @Override
    public List<Tour> sortByQuantity() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByQuantity();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }
    
    @Override
    public String updateForCanceling(Tour tour, Integer quantity) {
        tourRepository.updateQuantity(tour.getGeneralQuantity() - quantity,
                tour.getTourName(), tour.getTourDate());
        return "Successfully has been deleted";
    }
    
    @Override
    public String updateForBooking(Tour tour, Integer quantity) {
        tourRepository.updateQuantity(tour.getGeneralQuantity() + quantity,
                tour.getTourName(), tour.getTourDate());
        return "Successfully has been deleted";
    }
    


    @Override
    public String updateStartTime(LocalTime newStartTime, String tourName, LocalDate tourDate) {
        tourRepository.updateStartTime(newStartTime, tourName, tourDate);
        return "Update has been done successfully";
    }

    @Override
    public String updateCost(double newCost, String tourName, LocalDate tourDate) {
        tourRepository.updateCost(newCost,tourName,tourDate);
        return "Update has been done successfully";
    }

    @Override
    public String updateMaxQuantity(Integer newMaxQuantity, String tourName, LocalDate tourDate) {
        tourRepository.updateMaxQuantity(newMaxQuantity,tourName,tourDate);
        return "Update has benn done successfully";
    }

    @Override
    public Long getId (Tour tour) {
        return tourRepository.findTour(tour.getTourName(),tour.getTourDate()).get().getId();
    }
    
    @Override
    public TourEntity getTour (Tour tour) {
        return tourRepository.findTour(tour.getTourName(), tour.getTourDate()).get();
    }


    @Override
    public String update(Tour tour) {
        boolean check = false;
        if (tour.getStartTime() != null){
            check = true;
            updateStartTime(tour.getStartTime(),tour.getTourName(),tour.getTourDate());
        }
        if (tour.getMaxQuantity() != null){
            if (tour.getMaxQuantity() <=50 && tour.getMaxQuantity() >= 7){
                check = true;
                updateMaxQuantity(tour.getMaxQuantity(),tour.getTourName(),tour.getTourDate());
            } else throw new IllegalArgumentException("MaxQuantity Can Not Be More Than 50 and Can Not Be Less Than 7");
        }
        if (tour.getCost() != null){
            check = true;
            updateCost(tour.getCost(),tour.getTourName(),tour.getTourDate());
        }
        if (check){
            return "Updates have been done successfully";
        }
        throw new IllegalArgumentException("All Fields Are Null");
    }
}
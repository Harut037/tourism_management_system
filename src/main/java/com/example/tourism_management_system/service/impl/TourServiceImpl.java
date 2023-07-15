package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    @Autowired
    private UserInTourService userInTourService;
    private final TransactionService transactionService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, TransactionService transactionService) {
        this.tourRepository = tourRepository;
        this.transactionService = transactionService;
    }

    /**
     * Saves a new tour by creating a TourEntity object and persisting it in the tour repository.
     *
     * @param tour the Tour object to be saved
     * @return a string indicating the success of the operation
     */
    @Override
    public String save(Tour tour) {
        TourEntity tourEntity = new TourEntity(tour);
        tourRepository.save(tourEntity);
        return "The tour has been successfully created";
    }

    /**
     * Retrieves a list of all tours from the tour repository.
     *
     * @return a list of Tour objects representing all tours
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

    /**
     * Retrieves a list of all active tours from the system.
     *
     * @return a list of Tour objects representing all active tours
     */
    @Override
    public List<Tour> getAllActiveTours() {
        return null;
    }

    /**
     * Retrieves a list of all tours available for scheduling.
     *
     * @return a list of TourEntity objects representing the tours available for scheduling
     */
    @Override
    public List<TourEntity> getAllForSchedule() {
        return tourRepository.findAll();
    }

    /**
     * Retrieves a tour by its ID.
     *
     * @param id the ID of the tour to retrieve
     * @return the Tour object with the specified ID, or null if not found
     */
    @Override
    public Tour getById(Long id) {
        Optional<TourEntity> op = tourRepository.findById(id);
        return op.map(Tour::new).orElse(null);
    }
    
    @Override
    public TourEntity getEntityById (Long id) {
        return tourRepository.findById(id).get();
    }
    
    /**
     * Deletes a tour by its ID.
     *
     * @param id the ID of the tour to delete
     * @return a string indicating the result of the deletion operation
     */
    @Override
    public String deleteById(Long id) {
        List<String> transactionNumbers = userInTourService.getTransactionNumbers(id);
        String result = transactionService.revertTransactionList(transactionNumbers);
        if (result.equals("Success")) {
            for (String transactionNumber : transactionNumbers) {
                userInTourService.cancelByUs(transactionNumber);
            }
            if (tourRepository.findById(id).isPresent()) {
                tourRepository.delete(id, Status.DELETED);
                return "Successfully has been deleted";
            } else return "This id does not exist.";
        } else {
            throw new IllegalArgumentException("Could Not Delete");
        }
    }

    /**
     * Retrieves a list of tours sorted by cost in ascending order.
     *
     * @return a list of Tour objects representing the tours sorted by cost
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
     * Retrieves a list of tours sorted by date in ascending order.
     *
     * @return a list of Tour objects representing the tours sorted by date
     */
    @Override
    public List<Tour> sortByDate() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByTourDate();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }

    /**
     * Retrieves a list of tours sorted by distance in ascending order.
     *
     * @return a list of Tour objects representing the tours sorted by distance
     */
    @Override
    public List<Tour> sortByDistance() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByDistance();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }

    /**
     * Retrieves a list of tours sorted by quantity in ascending order.
     *
     * @return a list of Tour objects representing the tours sorted by quantity
     */
    @Override
    public List<Tour> sortByQuantity() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByQuantity();
        List<Tour> tours = new ArrayList<>();
        for (TourEntity i : tourEntities)
            tours.add(new Tour(i));
        return tours;
    }

    /**
     * Updates the quantity of a tour for canceling a certain number of spots.
     *
     * @param tour     the Tour object for which to update the quantity
     * @param quantity the number of spots to cancel
     * @return a string indicating the success of the update operation
     */
    @Override
    public String updateForCanceling(Tour tour, Integer quantity) {
        tourRepository.updateQuantity(tour.getGeneralQuantity() - quantity,
                tour.getTourName(), tour.getTourDate());
        return "Successfully has been deleted";
    }

    /**
     * Updates the quantity of a tour for booking a certain number of spots.
     *
     * @param tour     the Tour object for which to update the quantity
     * @param quantity the number of spots to book
     * @return a string indicating the success of the update operation
     */
    @Override
    public String updateForBooking(Tour tour, Integer quantity) {
        tourRepository.updateQuantity(tour.getGeneralQuantity() + quantity,
                tour.getTourName(), tour.getTourDate());
        return "Successfully has been deleted";
    }

    /**
     * Updates the start time of a tour with the specified tour name and date.
     *
     * @param newStartTime the new start time to update
     * @param tourName     the name of the tour
     * @param tourDate     the date of the tour
     * @return a string indicating the success of the update operation
     */
    @Override
    public String updateStartTime(LocalTime newStartTime, String tourName, LocalDate tourDate) {
        tourRepository.updateStartTime(newStartTime, tourName, tourDate);
        return "Update has been done successfully";
    }

    /**
     * Updates the cost of a tour with the specified tour name and date.
     *
     * @param newCost  the new cost to update
     * @param tourName the name of the tour
     * @param tourDate the date of the tour
     * @return a string indicating the success of the update operation
     */
    @Override
    public String updateCost(double newCost, String tourName, LocalDate tourDate) {
        tourRepository.updateCost(newCost, tourName, tourDate);
        return "Update has been done successfully";
    }

    /**
     * Updates the maximum quantity of a tour with the specified tour name and date.
     *
     * @param newMaxQuantity the new maximum quantity to update
     * @param tourName       the name of the tour
     * @param tourDate       the date of the tour
     * @return a string indicating the success of the update operation
     */
    @Override
    public String updateMaxQuantity(Integer newMaxQuantity, String tourName, LocalDate tourDate) {
        tourRepository.updateMaxQuantity(newMaxQuantity, tourName, tourDate);
        return "Update has benn done successfully";
    }


    /**
     * Retrieves the ID of a tour by searching for it based on the tour name and date.
     *
     * @param tour the Tour object for which to retrieve the ID
     * @return the ID of the tour
     */
    @Override
    public Long getId(Tour tour) {
        return tourRepository.findTour(tour.getTourName(), tour.getTourDate()).get().getId();
    }

    /**
     * Retrieves the TourEntity object corresponding to the specified Tour object based on the tour name and date.
     *
     * @param tour the Tour object for which to retrieve the TourEntity
     * @return the TourEntity object corresponding to the specified Tour
     */
    @Override
    public TourEntity getTour(Tour tour) {
        return tourRepository.findTour(tour.getTourName(), tour.getTourDate()).get();
    }

    /**
     * Updates the properties of a tour based on the specified Tour object.
     * The tour is updated with the new start time, maximum quantity, and cost if provided.
     *
     * @param tour the Tour object containing the updated tour information
     * @return a string indicating the success of the update operation
     * @throws IllegalArgumentException if all fields of the Tour object are null, or if the maximum quantity is outside the valid range
     */
    @Override
    public String update(Tour tour) {
        boolean check = false;
        if (tour.getStartTime() != null) {
            check = true;
            updateStartTime(tour.getStartTime(), tour.getTourName(), tour.getTourDate());
        }
        if (tour.getMaxQuantity() != null) {
            if (tour.getMaxQuantity() <= 50 && tour.getMaxQuantity() >= 7) {
                check = true;
                updateMaxQuantity(tour.getMaxQuantity(), tour.getTourName(), tour.getTourDate());
            } else throw new IllegalArgumentException("MaxQuantity Can Not Be More Than 50 and Can Not Be Less Than 7");
        }
        if (tour.getCost() != null) {
            check = true;
            updateCost(tour.getCost(), tour.getTourName(), tour.getTourDate());
        }
        if (check) {
            return "Updates have been done successfully";
        }
        throw new IllegalArgumentException("All Fields Are Null");
    }
    
    @Override
    public void doneById (Long id) {
        tourRepository.done(id, Status.DONE);
    }
}
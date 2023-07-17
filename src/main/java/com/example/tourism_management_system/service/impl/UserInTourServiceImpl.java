package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.UserInTourRepository;
import com.example.tourism_management_system.service.ReviewService;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserInTourService;
import com.example.tourism_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserInTourServiceImpl implements UserInTourService {

    private final UserInTourRepository userInTourRepository;
    @Autowired
    private TourService tourService;
    @Autowired
    private UserService userService;
    private final ReviewService reviewService;

    @Autowired
    public UserInTourServiceImpl(UserInTourRepository userInTourRepository, ReviewService reviewService) {
        this.userInTourRepository = userInTourRepository;
        this.reviewService = reviewService;
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
    public String save(UserInTour userInTour, Tour tour, String email) {
        UserEntity userEntity = userService.getUser(email);
        TourEntity tourEntity = tourService.getTour(tour);
        UserInTourEntity userInTourEntity = new UserInTourEntity(userInTour);
        userInTourEntity.setUser(userEntity);
        userInTourEntity.setTour(tourEntity);
        userInTourEntity = userInTourRepository.save(userInTourEntity);
        if (userInTourEntity.getId() != null) {
            tourService.updateForBooking(userInTour.getTour(), userInTour.getQuantity());
            return "Successfully";
        }
        throw new IllegalArgumentException("Error Occurred");
    }

    /**
     * Retrieves a list of UserInTour objects associated with the specified UserEntity.
     *
     * @param user the UserEntity object for which to retrieve the associated UserInTour objects
     * @return a list of UserInTour objects representing the user's participation in tours
     */
    @Override
    public List<UserInTour> findByUser(UserEntity user) {
        List<UserInTour> userInTours = new ArrayList<>();
        List<UserInTourEntity> userInTourEntities = userInTourRepository.findByUser(user, Status.DONE);
        if (userInTourEntities == null) {
            return Collections.emptyList();
        }
        for (UserInTourEntity userInTourEntity : userInTourEntities) {
            userInTours.add(new UserInTour(userInTourEntity));
        }
        return userInTours;
    }

    /**
     * Cancels the user's participation in a tour.
     *
     * @param userInTour the UserInTour object representing the user's participation to cancel
     * @return a string indicating the success of the cancellation operation
     * @throws IllegalArgumentException if an error occurs during the cancellation
     */
    @Override
    public String cancel(UserInTour userInTour) {
        int result = userInTourRepository.cancel(userInTour.getTransactionNumber(), Status.CANCELED);
        if (result > 0) {
            return tourService.updateForCanceling(userInTour.getTour(), userInTour.getQuantity());
        }
        throw new IllegalArgumentException("Error occurred While cancelling");
    }

    /**
     * Retrieves the UserInTour object associated with the specified transaction number.
     *
     * @param transactionNumber the transaction number for which to retrieve the UserInTour object
     * @return the UserInTour object associated with the transaction number
     */
    @Override
    public UserInTour getUserInTour(String transactionNumber) {
        return new UserInTour(userInTourRepository.getUserInTourEntity(transactionNumber));
    }

    /**
     * Adds a review to the UserInTour object.
     *
     * @param userInTour the UserInTour object to which the review will be added
     * @param reviewId   the ID of the review to add
     * @return the number of rows affected in the database
     */
    @Override
    public Integer addReview(UserInTour userInTour, Long reviewId) {
        return userInTourRepository.addReview(userInTourRepository.getId(userInTour.getTransactionNumber()), reviewService.getById(reviewId));
    }

    /**
     * Retrieves a list of UserInTour objects associated with the specified Tour.
     *
     * @param tour the Tour object for which to retrieve the associated UserInTour objects
     * @return a list of UserInTour objects representing the users participating in the tour
     */
    @Override
    public List<UserInTour> findByTour(Tour tour) {
        TourEntity tourEntity = tourService.getTour(tour);
        List<UserInTourEntity> userInTourEntities = userInTourRepository.findByTour(tourEntity);
        List<UserInTour> userInTours = new ArrayList<>();
        userInTourEntities.forEach(userInTourEntity -> userInTours.add(new UserInTour(userInTourEntity)));
        return userInTours;
    }
    
    @Override
    public List <String> getTransactionNumbers (Long tourId) {
        TourEntity tourEntity = tourService.getEntityById(tourId);
        return userInTourRepository.getTransactionNumbers(tourEntity);
    }
    
    @Override
    public void cancelByUs (String transactionNumber) {
        userInTourRepository.cancel(transactionNumber, Status.CANCELED);
    }
}
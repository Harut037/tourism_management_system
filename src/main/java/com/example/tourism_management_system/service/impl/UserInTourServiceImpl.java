package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
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
    private final TourService          tourService;
    @Autowired
    private UserService userService;
    private final ReviewService reviewService;
    
    @Autowired
    public UserInTourServiceImpl(UserInTourRepository userInTourRepository, TourService tourService, ReviewService reviewService) {
        this.userInTourRepository = userInTourRepository;
        this.tourService = tourService;
        this.reviewService = reviewService;
    }
    
    @Override
    public String save (UserInTour userInTour, Tour tour, String email) {
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
    
    @Override
    public List <UserInTour> findByUser (UserEntity user) {
        List<UserInTour> userInTours = new ArrayList <>();
        List<UserInTourEntity> userInTourEntities = userInTourRepository.findByUser(user);
        if (userInTourEntities == null) {
            return Collections.emptyList();
        }for (UserInTourEntity userInTourEntity : userInTourEntities){
            userInTours.add(new UserInTour(userInTourEntity));
        }
        return userInTours;
    }
    @Override
    public String cancel (UserInTour userInTour) {
        int result = userInTourRepository.cancel(userInTour.getTransactionNumber());
        if (result > 0){
            return tourService.updateForCanceling(userInTour.getTour(), userInTour.getQuantity());
        }
        throw new IllegalArgumentException("Error occurred While cancelling");
    }
    
    @Override
    public UserInTour getUserInTour (String transactionNumber) {
        return new UserInTour(userInTourRepository.getUserInTourEntity(transactionNumber));
    }
    
    @Override
    public Integer addReview (UserInTour userInTour, Long reviewId) {
        return userInTourRepository.addReview(userInTourRepository.getId(userInTour.getTransactionNumber()), reviewService.getById(reviewId));
    }
    
    @Override
    public List <UserInTour> findByTour (Tour tour) {
        TourEntity tourEntity = tourService.getTour(tour);
        List<UserInTourEntity> userInTourEntities = userInTourRepository.findByTour(tourEntity);
        List<UserInTour> userInTours = new ArrayList <>();
        userInTourEntities.forEach(userInTourEntity -> userInTours.add(new UserInTour(userInTourEntity)));
        return userInTours;
    }
}
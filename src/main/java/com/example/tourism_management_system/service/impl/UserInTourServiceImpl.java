package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.pojos.BookTour;
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
    public String save (UserInTour userInTour) {
        UserInTourEntity userInTourEntity = userInTourRepository.save(new UserInTourEntity(userInTour));
        if (userInTourEntity.getId() != null) {
            return "Successfully";
        }
        return "Error";
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
            return tourService.update(userInTour.getQuantity());
        }
        throw new IllegalArgumentException("Error occurred While cancelling");
    }
    
    @Override
    public UserInTour getUserInTour (Tour tour, String email) {
        return userInTourRepository.getUserInTour(tourService.getTour(tour), userService.getUser(email));
    }
    
    @Override
    public Integer addReview (UserInTour userInTour, Long reviewId) {
        return userInTourRepository.addReview(userInTourRepository.getId(tourService.getTour(userInTour.getTour()), userService.getUser(userInTour.getUser().getEmail())), reviewService.getById(reviewId));
    }
    
    @Override
    public Integer edit (BookTour bookTour, String email) {
        UserInTour userInTour = userInTourRepository.getUserInTour(tourService.getTour(bookTour.getTour()), userService.getUser(email));
        userInTour.setQuantity(bookTour.getQuantity());
        return userInTourRepository.changeQuantity(tourService.getTour(bookTour.getTour()), userService.getUser(email), bookTour.getQuantity());
    }
}
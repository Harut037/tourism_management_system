package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInTourService {
    String save(UserInTour userInTour, Tour tour, String email);

    List<UserInTour> findByUser(UserEntity user);

    String cancel(UserInTour userInTour);

    UserInTour getUserInTour(String transactionNumber);

    Integer addReview(UserInTour userInTour, Long reviewId);

    List<UserInTour> findByTour(Tour tour);
    
    List<String> getTransactionNumbers (Long tourId);
    
    void cancelByUs (String transactionNumber);
}
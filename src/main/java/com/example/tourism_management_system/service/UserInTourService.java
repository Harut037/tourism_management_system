package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.BookTour;
import com.example.tourism_management_system.model.pojos.Review;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInTourService {
    String save(UserInTour userInTour);
    
    List<UserInTour> findByUser (UserEntity user);
    
    String cancel (UserInTour userInTour);
    
    UserInTour getUserInTour (String transactionNumber);
    
    Integer addReview (UserInTour userInTour, Long reviewId);
}

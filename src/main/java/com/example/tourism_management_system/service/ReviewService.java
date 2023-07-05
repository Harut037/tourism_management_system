package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.pojos.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    Long save(Review review);

    ReviewEntity getById(Long reviewId);
}
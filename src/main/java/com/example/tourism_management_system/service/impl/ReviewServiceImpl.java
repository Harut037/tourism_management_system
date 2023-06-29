package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.pojos.Review;
import com.example.tourism_management_system.repository.ReviewRepository;
import com.example.tourism_management_system.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    @Autowired
    public ReviewServiceImpl (ReviewRepository reviewRepository) {this.reviewRepository = reviewRepository;}
    
    @Override
    public Long save (Review review) {
        ReviewEntity reviewEntity = reviewRepository.save(new ReviewEntity(review));
        return reviewEntity.getId();
    }
    
    @Override
    public ReviewEntity getById (Long reviewId) {
        return reviewRepository.getById(reviewId);
    }
}
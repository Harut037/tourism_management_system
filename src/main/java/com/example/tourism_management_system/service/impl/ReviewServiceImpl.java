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
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Saves a Review object by creating and persisting a corresponding ReviewEntity object.
     *
     * @param review the Review object to save
     * @return the ID of the saved review
     */
    @Override
    public Long save(Review review) {
        ReviewEntity reviewEntity = reviewRepository.save(new ReviewEntity(review));
        return reviewEntity.getId();
    }

    /**
     * Retrieves a ReviewEntity object by its ID.
     *
     * @param reviewId the ID of the review to retrieve
     * @return the ReviewEntity object with the specified ID
     */
    @Override
    public ReviewEntity getById(Long reviewId) {
        return reviewRepository.getReferenceById(reviewId);
    }
}
package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInTourRepository extends JpaRepository<UserInTourEntity, Long> {
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.user = :user")
    List<UserInTourEntity> findByUser (UserEntity user);
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.tour = :tour")
    List<UserInTourEntity> findByTour(TourEntity tour);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.status = 'CANCELED' where u.transactionNumber = :transactionNumber")
    int cancel (String transactionNumber);
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.transactionNumber = :transactionNumber")
    UserInTourEntity getUserInTourEntity (String transactionNumber);
    
    @Transactional
    @Query("SELECT u.id FROM UserInTourEntity u WHERE u.transactionNumber = :transactionNumber")
    Long getId (String transactionNumber);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.reviewEntity = :reviewEntity where u.id = :id")
    int addReview (Long id, ReviewEntity reviewEntity);
}
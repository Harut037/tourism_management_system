package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInTourRepository extends JpaRepository<UserInTourEntity, Long> {

    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.user = :user AND u.status = :status")
    List<UserInTourEntity> findByUser(UserEntity user, Status status);

    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.tour = :tour")
    List<UserInTourEntity> findByTour(TourEntity tour);

    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.transactionNumber = :transactionNumber")
    UserInTourEntity getUserInTourEntity(String transactionNumber);

    @Transactional
    @Query("SELECT u.id FROM UserInTourEntity u WHERE u.transactionNumber = :transactionNumber")
    Long getId(String transactionNumber);

    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.reviewEntity = :reviewEntity where u.id = :id")
    int addReview(Long id, ReviewEntity reviewEntity);
    
    @Transactional
    @Query("SELECT u.transactionNumber FROM UserInTourEntity u WHERE u.tour = :tourEntity")
    List<String> getTransactionNumbers (TourEntity tourEntity);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.status = :status where u.transactionNumber = :transactionNumber")
    int cancel (String transactionNumber, Status status);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.status = :status where u.tour = :tour")
    void done (TourEntity tour, Status status);
}
package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInTourRepository extends JpaRepository<UserInTourEntity, Long> {
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.id = :userId")
    List<UserInTourEntity> findByUserId (Long userId);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInTourEntity u SET u.flag = false where u.transactionNumber = :transactionNumber")
    String cancel (String transactionNumber);
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.user = :userId AND u.tour = :tourId")
    UserInTour getUserInTour (Long tourId, Long userId);
}
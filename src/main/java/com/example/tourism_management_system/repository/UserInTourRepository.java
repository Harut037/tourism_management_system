package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInTourRepository extends JpaRepository<UserInTourEntity, Long> {
    
    @Transactional
    @Query("SELECT u FROM UserInTourEntity u WHERE u.id = :userId")
    List<UserInTourEntity> findByUserId (Long userId);
    
}
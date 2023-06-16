package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInTourRepository extends JpaRepository<UserInTourEntity, Long> {
    
    List<UserInTourEntity> findByUserId (Long userId);
}
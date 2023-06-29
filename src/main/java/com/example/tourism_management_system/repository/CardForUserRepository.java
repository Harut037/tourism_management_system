package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CardForUserRepository extends JpaRepository<CardEntityForUser, Long> {
    
    @Transactional
    @Modifying
    @Query("UPDATE CardEntityForUser c SET c.flag = false")
    int deleteByCardNumber (String cardNumber);
}
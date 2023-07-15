package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CardForUserRepository extends JpaRepository<CardEntityForUser, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE CardEntityForUser c SET c.status = :status WHERE c.cardNumber = :cardNumber")
    int deleteByCardNumber(String cardNumber, Status status);
}
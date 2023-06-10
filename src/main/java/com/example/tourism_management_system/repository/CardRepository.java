package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findCardEntityByCardNumber(String cardNumber);

    CardEntity findCardEntitiesByCardNumber(String cardNumber);

}
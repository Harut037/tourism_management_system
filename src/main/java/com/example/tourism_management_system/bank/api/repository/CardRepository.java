package com.example.tourism_management_system.bank.api.repository;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findCardEntityByCardNumber(String cardNumber);

    @Transactional
    @Query("SELECT c FROM CardEntity c  WHERE c.cardNumber = :cardNumber AND c.owner = :owner and c.expirationDate = :expirationDate and c.cvv = :cvv")
    Optional<CardEntity> findCard(String cardNumber, String owner, LocalDate expirationDate, String cvv);

    @Transactional
    @Query("SELECT c FROM CardEntity c  WHERE c.cardNumber = :cardNumber")
    Optional<CardEntity> findCardWithNumber(String cardNumber);
}
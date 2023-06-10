package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    TransactionEntity findTransactionEntityByTransactionNumber(String number);

}
package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    public TransactionEntity findTransactionEntityByTransactionNumber(String number);


}

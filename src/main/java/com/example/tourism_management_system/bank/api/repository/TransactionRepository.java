package com.example.tourism_management_system.bank.api.repository;

import com.example.tourism_management_system.bank.api.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE TransactionEntity t SET t.flag = false  where t.transactionNumber = :transactionNumber")
    void revert(String transactionNumber);

    @Transactional
    @Query("SELECT c FROM TransactionEntity c  WHERE c.transactionNumber = :transactionNumber")
    Optional<TransactionEntity> findTransaction(String transactionNumber);
}
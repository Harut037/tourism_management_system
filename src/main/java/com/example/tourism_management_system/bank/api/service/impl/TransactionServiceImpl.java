package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.entity.TransactionEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.repository.TransactionRepository;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

     ValidationForCard validationForCard;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String makeTransaction(Card card, double price) {
        TransactionEntity transactionEntity = new TransactionEntity(card,price);
        transactionEntity.setTransactionNumber(validationForCard.generateTransactionNumber());
        transactionRepository.save(transactionEntity);
        return "Validation passed successfully";
    }

    @Override
    public String revertTransaction(String transactionNumber) {
        return transactionRepository.revert(transactionNumber);
    }
}

package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.entity.TransactionEntity;
import com.example.tourism_management_system.bank.api.model.enumForCard.Currency;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.repository.TransactionRepository;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

     private final ValidationForCard validationForCard;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ValidationForCard validationForCard) {
        this.transactionRepository = transactionRepository;
        this.validationForCard = validationForCard;
    }

    @Override
    public String makeTransaction(Card card, double price) {
        TransactionEntity transactionEntity = new TransactionEntity(card,price);
        transactionEntity.setTransactionNumber(validationForCard.generateTransactionNumber());
        transactionEntity.setCurrency(card.getCurrency());
        transactionRepository.save(transactionEntity);
        return transactionEntity.getTransactionNumber();
    }

    @Override
    public String revertTransaction(String transactionNumber) {
        return transactionRepository.revert(transactionNumber);
    }
}
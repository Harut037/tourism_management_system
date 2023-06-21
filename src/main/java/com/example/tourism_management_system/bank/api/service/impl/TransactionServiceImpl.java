package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public String makeTransaction(CardForUser cardForUser, Double price) {
        return null;
    }

    @Override
    public String revertTransaction(String transactionNumber) {
        return null;
    }
}
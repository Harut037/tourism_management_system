package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public String makeTransaction(Card card, Double price) {
        return null;
    }

    @Override
    public String revertTransaction(String transactionNumber) {
        return null;
    }
}

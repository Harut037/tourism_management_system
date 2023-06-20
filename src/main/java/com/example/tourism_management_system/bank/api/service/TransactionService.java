package com.example.tourism_management_system.bank.api.service;

import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    
    String makeTransaction (CardForUser cardForUser, Double price);
    
    String revertTransaction(String transactionNumber);
}
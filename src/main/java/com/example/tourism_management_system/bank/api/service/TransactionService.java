package com.example.tourism_management_system.bank.api.service;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    String makeTransaction (Card card, double price);
    
    String revertTransaction(String transactionNumber);
    
    String revertTransactionList (List<String> transactionNumbers);
}
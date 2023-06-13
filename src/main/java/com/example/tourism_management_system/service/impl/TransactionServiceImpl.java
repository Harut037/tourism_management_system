package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.pojos.Transaction;
import com.example.tourism_management_system.repository.TransactionRepository;
import com.example.tourism_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    @Override
    public Boolean makeTransaction (Transaction transaction) {
        return null;
    }
}
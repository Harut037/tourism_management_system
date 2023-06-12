package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Boolean makeTransaction(Transaction transaction);
}

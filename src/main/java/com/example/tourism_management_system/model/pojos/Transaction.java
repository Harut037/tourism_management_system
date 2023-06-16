package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String transactionNumber;
    private Card sender;
    private String date;
    private double balance;
    private String currency;
    

    public Transaction (TransactionEntity entity){
    
    }
}
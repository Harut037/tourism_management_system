package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionNumber;

    private String sender;

    private String receiver;

    private String date;

    private double balance;

    private String currency;
    
    @Column ( name = "flag", nullable = false )
    private           Boolean               flag;


    public TransactionEntity(String transactionNumber, String sender, String receiver, String date, double balance, String currency) {
        this.transactionNumber = transactionNumber;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.balance = balance;
        this.currency = currency;
    }

    public TransactionEntity() {

    }
}

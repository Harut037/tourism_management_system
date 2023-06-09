package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.TransactionEntity;

public class Transaction {

    private String transactionNumber;
    private Card sender;
    private String date;
    private double balance;
    private String currency;
    
    public Transaction () {}

    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Card getSender() {
        return sender;
    }

    public void setSender(Card sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public Transaction (TransactionEntity entity){
    
    }
    
}
package com.example.tourism_management_system.model.pojos;

public class TransactionModel {

    private String transactionNumber;

    private String sender;

    private String receiver;

    private String date;

    private double balance;

    private String currency;




    public TransactionModel(Long senderId, Long receiverId, String transactionNumber, String transactionType, String sender, String receiver, String date, double balance, String currency) {
        this.transactionNumber = transactionNumber;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.balance = balance;
        this.currency = currency;
    }


    public String getCurrency() {
        return currency;
    }
    public double getBalance() {
        return balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionModel() {
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


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }


    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
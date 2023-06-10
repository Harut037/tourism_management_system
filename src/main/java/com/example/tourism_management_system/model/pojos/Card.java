package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.CardEntity;

import java.time.LocalDate;

public class Card {

    private String firstName;
    private String lastName;
    private String cardNumber;
    private String type;
    private double balance;
    private LocalDate expirationDate;
    private String status;
    private String currency;


    public Card() {
    }

    public Card(String firstName, String lastName, String cardNumber, String type, double balance, LocalDate expirationDate, String status, String currency) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.type = type;
        this.balance = balance;
        this.expirationDate = expirationDate;
        this.status = status;
        this.currency = currency;
    }

    public Card(CardEntity cardEntity) {
        setCardNumber(cardEntity.getCardNumber());
        setFirstName(cardEntity.getFirstName());
        setLastName(cardEntity.getLastName());
        setType(cardEntity.getType());
        setBalance(cardEntity.getBalance());
        setExpirationDate(cardEntity.getExpirationDate());
        setStatus(cardEntity.getStatus());
        setCurrency(cardEntity.getCurrency());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
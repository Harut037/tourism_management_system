package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.enumForCard.CardType;
import com.example.tourism_management_system.model.pojos.Card;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull
    @Column(name = "card_number")
    private String cardNumber;
    
    @NotNull
    @Column(name = "type")
    private String type;
    
    @NotNull
    @Column(name = "balance")
    private double balance;
    
    @NotNull
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    
    @NotNull
    @Column(name = "status")
    private String status;
    
    @NotNull
    @Column(name = "currency")
    private String currency;
    
    @Column ( name = "flag", nullable = false )
    private           Boolean               flag;

    public CardEntity() {}

    public CardEntity(UserEntity user, String firstName, String lastName, String cardNumber, String type, double balance, LocalDate expirationDate, String status, String currency) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.type = type;
        this.balance = balance;
        this.expirationDate = expirationDate;
        this.status = status;
        this.currency = currency;
    }

    public CardEntity(Card card) {
        setCardNumber(card.getCardNumber());
        setFirstName(card.getFirstName());
        setLastName(card.getLastName());
        setType(card.getType());
        setBalance(card.getBalance());
        setExpirationDate(card.getExpirationDate());
        setStatus(CardType.valueOf("ACTIVE").toString());
        setCurrency(card.getCurrency());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
        balance = 0;
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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
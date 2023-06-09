package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.enumForCard.CardType;
import com.example.tourism_management_system.model.pojos.Card;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "User_Entity")
    private UserEntity user;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "card_number", nullable = false)
    private String cardNumber;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "balance", nullable = false)
    private double balance;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "currency", nullable = false)
    private String currency;
    
    @Column ( name = "flag", nullable = false )
    private           Boolean               flag;

    public CardEntity() {}



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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
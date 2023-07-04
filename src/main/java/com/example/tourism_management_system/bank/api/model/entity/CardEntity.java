package com.example.tourism_management_system.bank.api.model.entity;

import com.example.tourism_management_system.bank.api.model.enumForCard.Status;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "card_number", nullable = false)
    private String cardNumber;
    @Column(name = "cvv", nullable = false)
    private String cvv;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "balance", nullable = false)
    private double balance;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Enumerated ( EnumType.STRING )
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "currency", nullable = false)
    private String currency;

    public CardEntity(Card card) {
        this.owner = card.getOwner();
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
        this.type = card.getType();
        this.balance = card.getBalance();
        this.expirationDate = card.getExpirationDate();
        this.status = card.getStatus();
        this.currency = card.getCurrency();
    }
}
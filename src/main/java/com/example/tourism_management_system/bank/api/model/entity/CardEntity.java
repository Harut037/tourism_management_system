package com.example.tourism_management_system.bank.api.model.entity;

import com.example.tourism_management_system.bank.api.enumForCard.Status;
import com.example.tourism_management_system.model.pojos.CardForUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "BankCard")
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
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "balance", nullable = false)
    private Float balance;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "currency", nullable = false)
    private String currency;

    public CardEntity(CardForUser cardForUser) {
        setCardNumber(cardForUser.getCardNumber());
        setExpirationDate(cardForUser.getExpirationDate());
        setStatus(Status.valueOf("ACTIVE").toString());
    }

}
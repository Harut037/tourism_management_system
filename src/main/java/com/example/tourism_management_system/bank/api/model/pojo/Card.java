package com.example.tourism_management_system.bank.api.model.pojo;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.enumForCard.Status;
import com.example.tourism_management_system.model.pojos.CardForUser;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Pattern(regexp = "^[A-Z]+\\s[A-Z]+$")
    private String owner;
    private String cardNumber;
    private String type;
    private double balance;
    private LocalDate expirationDate;
    private Status status;
    private String cvv;
    private String currency;

    public Card(CardEntity cardEntity) {
        setCardNumber(cardEntity.getCardNumber());
        setOwner(cardEntity.getOwner());
        setType(cardEntity.getType());
        setBalance(cardEntity.getBalance());
        setExpirationDate(cardEntity.getExpirationDate());
        setStatus(cardEntity.getStatus());
        setCurrency(cardEntity.getCurrency());
        setCvv(cardEntity.getCvv());
    }

    public Card(CardForUser cardForUser) {
        setCardNumber(cardForUser.getCardNumber());
        setOwner(cardForUser.getOwner());
        setExpirationDate(cardForUser.getExpirationDate());
        setCvv(cardForUser.getCvv());
    }
}
package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String firstName;
    private String lastName;
    private String cardNumber;
    private String type;
    private double balance;
    private LocalDate expirationDate;
    private String status;
    private String currency;


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

}
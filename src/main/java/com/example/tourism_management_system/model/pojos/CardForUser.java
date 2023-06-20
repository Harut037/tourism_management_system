package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.entities.CardEntityForUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardForUser {
    
    private String    cardNumber;
    private String    cvv;
    private LocalDate expirationDate;
    
    public CardForUser (CardEntityForUser cardEntityForUser) {
        this.cardNumber = cardEntityForUser.getCardNumber();
        this.cvv = cardEntityForUser.getCvv();
        this.expirationDate = cardEntityForUser.getExpirationDate();
    }
    
    public CardForUser (Card card) {
        this.cardNumber = card.getCardNumber();
        //this.cvv = card.getCvv();
        this.expirationDate = card.getExpirationDate();
    }
}
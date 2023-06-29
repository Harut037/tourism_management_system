package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.entities.CardEntityForUser;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class CardForUser {
    
    @NonNull
    @Pattern(regexp = "^[A-Z]+\\s[A-Z]+$")
    private String owner;
    
    @NonNull
    @Pattern(regexp = "\\d{15,16}")
    private String    cardNumber;
    
    @NonNull
    @Pattern(regexp = "\\d{3}")
    private String    cvv;
    
    //TODO: validation
    @NonNull
    private LocalDate expirationDate;
    
    public CardForUser (CardEntityForUser cardEntityForUser) {
        this.owner = cardEntityForUser.getOwner();
        this.cardNumber = cardEntityForUser.getCardNumber();
        this.cvv = cardEntityForUser.getCvv();
        this.expirationDate = cardEntityForUser.getExpirationDate();
    }
    
    public CardForUser (Card card) {
        this.owner = card.getOwner();
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
        this.expirationDate = card.getExpirationDate();
    }
}
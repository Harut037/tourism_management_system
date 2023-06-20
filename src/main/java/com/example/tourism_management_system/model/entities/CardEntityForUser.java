package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.model.pojos.CardForUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Card_For_User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntityForUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long      id;
    @Column(nullable = false)
    private String    cardNumber;
    @Column(nullable = false)
    private String    cvv;
    @Column(nullable = false)
    private LocalDate expirationDate;
    
    public CardEntityForUser (CardForUser cardForUser) {
        this.cardNumber = cardForUser.getCardNumber();
        this.cvv = cardForUser.getCvv();
        this.expirationDate = cardForUser.getExpirationDate();
    }
    
    public CardEntityForUser (CardEntity card) {
        this.cardNumber = card.getCardNumber();
        //this.cvv = card.getCvv();
        this.expirationDate = card.getExpirationDate();
    }
}
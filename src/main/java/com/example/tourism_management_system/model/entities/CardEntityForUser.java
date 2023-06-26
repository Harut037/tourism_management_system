package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.CardForUser;
import com.example.tourism_management_system.model.pojos.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "card_for_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntityForUser {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long      id;
    @Column(name = "card_number", nullable = false)
    private String    cardNumber;
    @Column(name = "cvv", nullable = false)
    private String    cvv;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    
    public CardEntityForUser (CardForUser cardForUser) {
        this.cardNumber = cardForUser.getCardNumber();
        this.cvv = cardForUser.getCvv();
        this.expirationDate = cardForUser.getExpirationDate();
    }
}
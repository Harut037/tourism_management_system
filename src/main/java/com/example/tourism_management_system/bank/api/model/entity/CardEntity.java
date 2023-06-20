package com.example.tourism_management_system.bank.api.model.entity;

import com.example.tourism_management_system.bank.api.enumForCard.CardType;
import com.example.tourism_management_system.bank.api.enumForCard.Status;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table ( name = "Card" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long       id;
    @Column ( nullable = false )
    private String owner;
    @Column ( nullable = false )
    private String     cardNumber;
    @Column ( nullable = false )
    private String     type;
    @Column ( nullable = false )
    private Float      balance;
    @Column ( nullable = false )
    private LocalDate  expirationDate;
    @Column ( nullable = false )
    private String     status;
    @Column ( nullable = false )
    private String     currency;
    
    public CardEntity (Card card) {
        setCardNumber(card.getCardNumber());
        setOwner(card.getOwner());
        setType(card.getType());
        setBalance((float) card.getBalance());
        setExpirationDate(card.getExpirationDate());
        setStatus(Status.valueOf("ACTIVE").toString());
        setCurrency(card.getCurrency());
    }
    
}
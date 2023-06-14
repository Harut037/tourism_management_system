package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.enumForCard.CardType;
import com.example.tourism_management_system.model.pojos.Card;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @ManyToOne
    @JoinColumn ( name = "user_id", nullable = false )
    private UserEntity user;
    @Column ( nullable = false )
    private String     firstName;
    @Column ( nullable = false )
    private String     lastName;
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
    @Column ( nullable = false )
    private Boolean    flag;
    
    public CardEntity (Card card) {
        setCardNumber(card.getCardNumber());
        setFirstName(card.getFirstName());
        setLastName(card.getLastName());
        setType(card.getType());
        setBalance((float) card.getBalance());
        setExpirationDate(card.getExpirationDate());
        setStatus(CardType.valueOf("ACTIVE").toString());
        setCurrency(card.getCurrency());
    }
    
}
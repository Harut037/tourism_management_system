package com.example.tourism_management_system.bank.api.model.entity;

import com.example.tourism_management_system.bank.api.enumForCard.Status;
import com.example.tourism_management_system.model.pojos.CardForUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table ( name = "BankCard" )
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
    
    public CardEntity (CardForUser cardForUser) {
        setCardNumber(cardForUser.getCardNumber());
        setExpirationDate(cardForUser.getExpirationDate());
        setStatus(Status.valueOf("ACTIVE").toString());
    }
    
}
package com.example.tourism_management_system.bank.api.model.entity;


import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String transactionNumber;

    private String sender;
    @Column(nullable = false)
    private String receiver;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private Boolean flag;



    public TransactionEntity(Card card, double price){
        setSender(card.getCardNumber());
     setReceiver("4847040004125360");
        setDate(LocalDate.now().toString());
      setCurrency(card.getCurrency());
        setPrice(price);
        setCurrency(card.getCurrency());
        setFlag(true);
    }


}
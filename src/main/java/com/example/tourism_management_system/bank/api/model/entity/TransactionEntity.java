package com.example.tourism_management_system.bank.api.model.entity;


import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_number", nullable = false)
    private String transactionNumber;
    @Column(name = "sender", nullable = false)
    private String sender;
    @Column(name = "receiver", nullable = false)
    private String receiver;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "flag", nullable = false)
    private Boolean flag;


    public TransactionEntity(Card card, double price) {
        setSender(card.getCardNumber());
        setReceiver("4847040004125360");
        setDate(LocalDate.now().toString());
        setCurrency(card.getCurrency());
        setPrice(price);
        setCurrency(card.getCurrency());
        setFlag(true);
    }
}
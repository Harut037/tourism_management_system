package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity sender;
    @Column(nullable = false)
    private String receiver;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private Float balance;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private Boolean flag;

    public TransactionEntity(Transaction transaction) {

    }
    
}
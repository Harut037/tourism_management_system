package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long      id;
    @Column(nullable = false)
    private String    cardNumber;
    @Column(nullable = false)
    private String    cvv;
    @Column(nullable = false)
    private LocalDate expirationDate;
}
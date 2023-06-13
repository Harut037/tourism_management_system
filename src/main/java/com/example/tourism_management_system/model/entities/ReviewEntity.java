package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private           Long              id;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "driver")
    private Integer driver;
    @Column(name = "guide")
    private Integer guide;
    @Column(name = "a")
    private Integer a;
    @Column(name = "b")
    private Integer b;
    @Column(name = "c")
    private Integer c;
    @Column ( name = "flag", nullable = false )
    private           Boolean               flag = true;
}
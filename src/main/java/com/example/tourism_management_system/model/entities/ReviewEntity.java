package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Review")
public class ReviewEntity {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private           Long              id;
    @ManyToOne
    @JoinColumn(name = "User_In_Tour")
    private UserInTourEntity userInTourEntity;
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
}

package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name = "Review" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long    id;
    private String  feedback;
    @Column ( nullable = false )
    private Integer driver;
    @Column ( nullable = false )
    private Integer guide;
    @Column ( nullable = false )
    private Integer support;
    @Column ( nullable = false )
    private Integer tour;
    @Column ( nullable = false )
    private Integer company;
    @Column ( nullable = false )
    private Boolean flag = true;
}
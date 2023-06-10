package com.example.tourism_management_system.model.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    private transient String password;
    @Column(name = "password_hash", nullable = false)
    private Integer passwordHash;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;
}
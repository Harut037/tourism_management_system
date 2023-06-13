package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Card;
import com.example.tourism_management_system.model.pojos.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User_Entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private           String email;
    @Column(name = "birth_date", nullable = false)
    private           Date   birthDate;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
    @OneToMany(mappedBy = "user")
    private List<CardEntity> cardEntities;
    
    public UserEntity(User user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        //this.setAge(user.getAge());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCardEntities(castCards(user.getCards()));
    }
    
    private List<CardEntity> castCards(List<Card> cards) {
        if (cards != null) {
            List<CardEntity> cardEntities = new ArrayList<>();
            cards.forEach(card -> cardEntities.add(new CardEntity(card)));
            return cardEntities;
        }
        return null;
    }
}
package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "User_Entity")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "username")
    private String username;
    
    @NotNull
    @Column(name = "age")
    private Integer age;
    
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @NotNull
    @Column(name = "email")
    private String email;
    
    @OneToMany(mappedBy = "user")
    private List<CardEntity> cardEntities;
    
    public UserEntity () {}
    
    public UserEntity (User user) {
        this.setUsername(user.getUsername());
        this.setAge(user.getAge());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
    }
    
    public Long getId () {
        return id;
    }
    
    public void setId (Long id) {
        this.id = id;
    }
    
    public String getUsername () {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public Integer getAge () {
        return age;
    }
    
    public void setAge (Integer age) {
        this.age = age;
    }
    
    public String getPhoneNumber () {
        return phoneNumber;
    }
    
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail () {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public List <CardEntity> getCardEntities () {
        return cardEntities;
    }
    
    public void setCardEntities (List <CardEntity> cardEntities) {
        this.cardEntities = cardEntities;
    }
}
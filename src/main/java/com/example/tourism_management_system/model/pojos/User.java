package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserEntity;

public class User {
    
    private String username;
    
    private int age;
    
    private String phoneNumber;
    
    private String email;
    
    private CardModel card;
    
    public String getUsername () {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public CardModel getCard () {
        return card;
    }
    
    public void setCard (CardModel card) {
        this.card = card;
    }
    
    public int getAge () {
        return age;
    }
    
    public void setAge (int age) {
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
    
    public User (UserEntity user) {
        this.setUsername(user.getUsername());
        this.setAge(user.getAge());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
    }
}
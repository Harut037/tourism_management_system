package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.CardEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {

    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String lastName;
    private Date   birthDate;
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Pattern")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,20}$", message = "Invalid Password Pattern")
    private String password;
    @Pattern(regexp = "\\+374\\d{8}", message = "Invalid Phone Number Pattern")
    private String phoneNumber;
    private List<Card> cards;
    private List <UserInTour> userInTour;

    public User() {
    }

    public User(UserEntity user) {
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCards(castCards(user.getCardEntities()));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getBirthDate () {
        return birthDate;
    }
    
    public void setBirthDate (Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    public List <UserInTour> getUserInTour () {
        return userInTour;
    }
    
    public void setUserInTour (List <UserInTour> userInTour) {
        this.userInTour = userInTour;
    }
    
    private List<Card> castCards(List<CardEntity> cardEntities) {
        List<Card> cards = new ArrayList<>();
        cardEntities.forEach(card -> cards.add(new Card(card)));
        return cards;
    }
}
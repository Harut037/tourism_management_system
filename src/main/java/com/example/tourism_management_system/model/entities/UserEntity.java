package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Card;
import com.example.tourism_management_system.model.pojos.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User_Entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "age", nullable = false)
    private Integer age;
    private transient String password;
    @Column(name = "password_hash", nullable = false)
    private Integer passwordHash;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<CardEntity> cardEntities;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;


    public UserEntity() {
    }

    public UserEntity(User user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setAge(user.getAge());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCardEntities(castCards(user.getCards()));
        this.passwordHash = this.password.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(Integer passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CardEntity> getCardEntities() {
        return cardEntities;
    }

    public void setCardEntities(List<CardEntity> cardEntities) {
        this.cardEntities = cardEntities;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
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
package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private CardForUser cardForUser;

    public User(UserEntity userEntity) {
        this.setFirstName(userEntity.getFirstName());
        this.setLastName(userEntity.getLastName());
        this.setBirthDate(userEntity.getBirthDate());
        this.setEmail(userEntity.getEmail());
        this.setPhoneNumber(userEntity.getPhoneNumber());
        if (userEntity.getCardEntityForUser() != null) {
            this.setCardForUser(new CardForUser(userEntity.getCardEntityForUser()));
        }
    }
}
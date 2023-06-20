package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.entities.UserEntity;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Card card;
    private List <UserInTour> userInTour;



    public User(UserEntity user) {
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCard(new Card(user.getCardEntity()));
    }
}
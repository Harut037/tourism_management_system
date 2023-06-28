package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserEntity;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Date;
import java.util.List;


@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class User {
    @NonNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;
    @NonNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String lastName;
    @NonNull
    private Date   birthDate;
    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Pattern")
    private String email;
    @NonNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,20}$", message = "Invalid Password Pattern")
    private String password;
    @NonNull
    @Pattern(regexp = "\\+374\\d{8}", message = "Invalid Phone Number Pattern")
    private String            phoneNumber;
    private CardForUser       cardForUser;
    private List <UserInTour> userInTour;

    public User(UserEntity user) {
        this.setPhoneNumber(user.getPhoneNumber());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCardForUser(new CardForUser(user.getCardEntityForUser()));
    }
}
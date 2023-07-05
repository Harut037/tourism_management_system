package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.UserInTourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String phoneNumber;
    private CardForUser cardForUser;
    private List<UserInTour> userInTour;

    public User(UserEntity userEntity) {
        this.setFirstName(userEntity.getFirstName());
        this.setLastName(userEntity.getLastName());
        this.setBirthDate(userEntity.getBirthDate());
        this.setEmail(userEntity.getEmail());
        this.setPhoneNumber(userEntity.getPhoneNumber());
        this.setCardForUser(new CardForUser(userEntity.getCardEntityForUser()));
        this.setUserInTour(castUserInTours(userEntity.getUserInTourEntities()));
    }

    /**
     * Converts a list of UserInTourEntity objects to a list of UserInTour objects.
     *
     * @param userInTourEntities The list of UserInTourEntity objects to be converted.
     * @return The converted list of UserInTour objects.
     */
    private List<UserInTour> castUserInTours(List<UserInTourEntity> userInTourEntities) {
        if (userInTourEntities == null)
            return null;
        List<UserInTour> userInTours = new ArrayList<>();
        userInTourEntities.forEach(userInTourEntity -> userInTours.add(new UserInTour(userInTourEntity)));
        return userInTours;
    }
}
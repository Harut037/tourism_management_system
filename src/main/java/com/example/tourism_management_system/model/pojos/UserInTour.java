package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInTour {
    private User user;
    private Tour tour;
    private Integer quantity;
    private Transaction transaction;
    private Review review;

    public UserInTour(UserInTourEntity userInTourEntity) {
        this.setUser(new User(userInTourEntity.getUser()));
        this.setTour(new Tour(userInTourEntity.getTour()));
        this.setQuantity(userInTourEntity.getQuantity());
    }
}
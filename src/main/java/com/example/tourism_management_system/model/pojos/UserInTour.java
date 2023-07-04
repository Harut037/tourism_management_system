package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.UserInTourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInTour {
    
    private User         user;
    private Tour         tour;
    private Integer      quantity;
    private Double        price;
    private String transactionNumber;
    private Review        review;

    public UserInTour(UserInTourEntity userInTourEntity) {
        this.setUser(new User(userInTourEntity.getUser()));
        this.setTour(new Tour(userInTourEntity.getTour()));
        this.setQuantity(userInTourEntity.getQuantity());
        this.setPrice(userInTourEntity.getPrice());
        this.setTransactionNumber(userInTourEntity.getTransactionNumber());
        if (userInTourEntity.getReviewEntity() != null) {
            this.setReview(new Review(userInTourEntity.getReviewEntity()));
        }
    }
    
    public UserInTour (User user, Tour tour, Integer quantity) {
        this.setUser(user);
        this.setTour(tour);
        this.setQuantity(quantity);
        this.setPrice((double)this.quantity*this.tour.getCost());
    }
}
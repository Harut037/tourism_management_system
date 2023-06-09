package com.example.tourism_management_system.model.pojos;
import com.example.tourism_management_system.model.entities.UserInTourEntity;

public class UserInTour {
    private User user;
    private Tour tour;
    private Integer    quantity;
    
    public UserInTour () {}
    
    public UserInTour (UserInTourEntity userInTourEntity) {
        this.setUser(new User(userInTourEntity.getUserEntity()));
        this.setTour(new Tour(userInTourEntity.getTourEntity()));
        this.setQuantity(userInTourEntity.getQuantity());
    }
    
    public User getUser () {
        return user;
    }
    
    public void setUser (User user) {
        this.user = user;
    }
    
    public Tour getTour () {
        return tour;
    }
    
    public void setTour (Tour tour) {
        this.tour = tour;
    }
    
    public Integer getQuantity () {
        return quantity;
    }
    
    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }
}
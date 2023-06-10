package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.UserInTour;
import jakarta.persistence.*;

@Entity
@Table(name = "User_In_Tour")
public class UserInTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_Entity")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "Tour")
    private TourEntity tourEntity;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;


    public UserInTourEntity() {
    }

    public UserInTourEntity(UserInTour userInTour) {
        this.setUserEntity(new UserEntity(userInTour.getUser()));
        this.setTourEntity(new TourEntity(userInTour.getTour()));
        this.setQuantity(userInTour.getQuantity());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TourEntity getTourEntity() {
        return tourEntity;
    }

    public void setTourEntity(TourEntity tourEntity) {
        this.tourEntity = tourEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
package com.example.tourism_management_system.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pass_In_Tour")
public class PassInTourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "tour_id")
    private Long tourId;

    @NotNull
    @Column(name = "car_type")
    private String carType;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    public PassInTourEntity(Long userId, Long tourId, String carType, int quantity) {
        this.userId = userId;
        this.tourId = tourId;
        this.carType = carType;
        this.quantity = quantity;
    }

    public PassInTourEntity() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
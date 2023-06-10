package com.example.tourism_management_system.model.pojos;

public class PassInTour {
    
    private Long userId;
    
    private Long tourId;
    
    private String carType;
    
    private int quantity;
    
    public PassInTour(Long userId, Long tourId, String carType, int quantity) {
        this.userId = userId;
        this.tourId = tourId;
        this.carType = carType;
        this.quantity = quantity;
    }
    
    public Long getUserId () {
        return userId;
    }
    
    public void setUserId (Long userId) {
        this.userId = userId;
    }
    
    public Long getTourId () {
        return tourId;
    }
    
    public void setTourId (Long tourId) {
        this.tourId = tourId;
    }
    
    public String getCarType () {
        return carType;
    }
    
    public void setCarType (String carType) {
        this.carType = carType;
    }
    
    public int getQuantity () {
        return quantity;
    }
    
    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }
}
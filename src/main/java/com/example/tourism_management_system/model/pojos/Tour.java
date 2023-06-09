package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.TourEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tour {
    
    private String tourType;
    
    private String tourName;
    
    private LocalDate tourDate;
    
    private LocalTime startTime;
    
    private String duration;
    
    private String distance;
    
    private String carType;
    
    private int quantity;
    
    private int cost;

    public String getTourType () {
        return tourType;
    }
    
    public void setTourType (String tourType) {
        this.tourType = tourType;
    }
    
    public String getTourName () {
        return tourName;
    }
    
    public void setTourName (String tourName) {
        this.tourName = tourName;
    }
    
    public LocalDate getTourDate () {
        return tourDate;
    }
    
    public void setTourDate (LocalDate tourDate) {
        this.tourDate = tourDate;
    }
    
    public LocalTime getStartTime () {
        return startTime;
    }
    
    public void setStartTime (LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public String getDuration () {
        return duration;
    }
    
    public void setDuration (String duration) {
        this.duration = duration;
    }
    
    public String getDistance () {
        return distance;
    }
    
    public void setDistance (String distance) {
        this.distance = distance;
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
    
    public int getCost () {
        return cost;
    }
    
    public void setCost (int cost) {
        this.cost = cost;
    }
    
    public Tour(String tourType, String tourName, LocalDate tourDate, LocalTime startTime, String duration, String distance, String carType, int quantity, int cost) {
        this.tourType = tourType;
        this.tourName = tourName;
        this.tourDate = tourDate;
        this.startTime = startTime;
        this.duration = duration;
        this.distance = distance;
        this.carType = carType;
        this.quantity = quantity;
        this.cost = cost;
    }


    public Tour(TourEntity tourEntity){
        setTourType(tourEntity.getTourType());
        setTourName(tourEntity.getTourName());
//        setTourDate(tourEntity.getTourDate());
//        setStartTime(tourEntity.getStartTime());
        setDuration(tourEntity.getDuration());
        setDistance(tourEntity.getDistance());
        setCarType(tourEntity.getCarType());
        setQuantity(tourEntity.getQuantity());
        setCost(tourEntity.getCost());
    }



}
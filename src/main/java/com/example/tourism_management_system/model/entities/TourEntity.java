package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "Tours")
public class TourEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tour_type")
    private String tourType;

    @NotNull
    @Column(name = "tour_name")
    private String tourName;

    @NotNull
    @Column(name = "tour_date")
    private LocalDate tourDate;

    @NotNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @NotNull
    @Column(name = "distance")
    private String distance;

    @NotNull
    @Column(name = "duration")
    private String duration;
    
    @NotNull
    @Column(name = "car_type")
    private String carType;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @Column(name = "cost")
    private int cost;
    
    @Column ( name = "flag", nullable = false )
    private           Boolean               flag;

    public TourEntity(String tourType, String tourName, LocalDate tourDate, LocalTime startTime, String duration, String distance, String carType, int quantity, int cost) {
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

    public TourEntity() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public LocalDate getTourDate() {
        return tourDate;
    }

    public void setTourDate(LocalDate tourDate) {
        this.tourDate = tourDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public TourEntity (Tour tour){
        setTourType(tour.getTourType());
        setTourName(tour.getTourName());
        setTourDate(tour.getTourDate());
        setStartTime(tour.getStartTime());
        setDuration(tour.getDuration());
        setDistance(tour.getDistance());
        setCarType(tour.getCarType());
        setQuantity(tour.getQuantity());
        setCost(tour.getCost());
    }
}
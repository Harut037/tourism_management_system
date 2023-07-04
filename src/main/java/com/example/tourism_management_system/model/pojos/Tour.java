package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.enumForTour.Transport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class Tour {
    private String tourType;
    private String tourName;
    private LocalDate tourDate;
    private LocalTime startTime;
    private String duration;
    private String    distance;
    private Transport carType;
    private Integer   generalQuantity ;
    private Integer maxQuantity;
    private Integer cost;

    public Tour(TourEntity tourEntity){
        setTourType(tourEntity.getTourType());
        setTourName(tourEntity.getTourName());
        setTourDate(tourEntity.getTourDate());
        setStartTime(tourEntity.getStartTime());
        setDuration(tourEntity.getDuration());
        setDistance(tourEntity.getDistance());
        setCarType(tourEntity.getCarType());
        setGeneralQuantity((tourEntity.getGeneralQuantity()));
        setMaxQuantity(tourEntity.getMaxQuantity());
        setCost(tourEntity.getCost());
    }
    
    public Tour(CreateTour createTour){
        setTourType(createTour.getTourType());
        setTourName(createTour.getTourName());
        setTourDate(createTour.getTourDate());
        setStartTime(createTour.getStartTime());
        setMaxQuantity(createTour.getMaxQuantity());
        setCost(createTour.getCost());
    }
}
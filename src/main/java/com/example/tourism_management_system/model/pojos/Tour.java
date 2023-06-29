package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.TourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class Tour {
    //TODO: regex and not null
    private String tourType;
    private String tourName;
    private LocalDate tourDate;
    private LocalTime startTime;
    private String duration;
    private String distance;
    private String carType;
    private Integer generalQuantity ;

    @NonNull
    private Integer maxQuantity;
    private Integer cost;
    private Boolean flag ;

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
        setFlag(tourEntity.getFlag());
    }
}
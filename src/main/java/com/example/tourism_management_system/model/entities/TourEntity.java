package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.enumForTour.Transport;
import com.example.tourism_management_system.model.pojos.Tour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tour", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tour_name", "tour_date"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tour_type", nullable = false)
    private String tourType;
    @Column(name = "tour_name", nullable = false)
    private String tourName;
    @Column(name = "tour_date", nullable = false)
    private LocalDate tourDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "distance", nullable = false)
    private String distance;
    @Column(name = "duration", nullable = false)
    private String duration;
    @Column(name = "car_type", nullable = false)
    private String carType;
    @Column(name = "general_quantity", nullable = false)
    private Integer generalQuantity;
    @Column(name = "max_quantity", nullable = false)
    private Integer maxQuantity;
    @Column(name = "cost", nullable = false)
    private Integer cost;
    @Column(name = "active", nullable = false)
    private Boolean flag;
    @OneToMany(mappedBy = "tour")
    private List <UserInTourEntity> userInTourEntities;
    
    public TourEntity (Tour tour) {
        setTourType(tour.getTourType());
        setTourName(tour.getTourName());
        setTourDate(tour.getTourDate());
        setStartTime(tour.getStartTime());
        setDuration(tour.getDuration());
        setDistance(tour.getDistance());
        setCarType(Transport.valueOf("UNDEFINED").toString());
        setGeneralQuantity(0);
        setMaxQuantity(tour.getMaxQuantity());
        setCost(tour.getCost());
        setFlag(tour.getFlag());
    }
    
    void setMaxQuantity(int maxQuantity) {
        if (maxQuantity>50){
            throw new IllegalArgumentException("MaxQuantity Can Not Be More Than 50");
        }
        if (maxQuantity<7){
            throw new IllegalArgumentException("MaxQuantity Can Not Be Less Than 7");
        }
        this.maxQuantity = maxQuantity;
    }
}
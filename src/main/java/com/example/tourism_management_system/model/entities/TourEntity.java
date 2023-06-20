package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Tour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table ( name = "Tour" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourEntity {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long                    id;
    @Column ( nullable = false )
    private String                  tourType;
    @Column ( nullable = false )
    private String                  tourName;
    @Column ( nullable = false )
    private LocalDate               tourDate;
    @Column ( nullable = false )
    private LocalTime               startTime;
    @Column ( nullable = false )
    private String                  distance;
    @Column ( nullable = false )
    private String                  duration;
    @Column ( nullable = false )
    private String                  carType;
    @Column ( nullable = false )
    private Integer                 generalQuantity;
    @Column ( nullable = false )
    private Integer                 maxQuantity;
    @Column ( nullable = false )
    private Integer                 cost;
    @Column ( name = "active", nullable = false )
    private Boolean                 flag;
    @OneToMany(mappedBy = "tour")
    private List <UserInTourEntity> userInTourEntities;
    
    public TourEntity (Tour tour) {
        setTourType(tour.getTourType());
        setTourName(tour.getTourName());
        setTourDate(tour.getTourDate());
        setStartTime(tour.getStartTime());
        setDuration(tour.getDuration());
        setDistance(tour.getDistance());
        setCarType(tour.getCarType());
        setGeneralQuantity(tour.getQuantity());
        setCost(tour.getCost());
        setFlag(tour.getFlag());
    }
}
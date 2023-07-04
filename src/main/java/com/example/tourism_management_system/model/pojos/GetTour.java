package com.example.tourism_management_system.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class GetTour {
    @NonNull
    private String    tourType;
    @NonNull
    private String    tourName;
    @NonNull
    private LocalDate tourDate;
    @NonNull
    private LocalTime startTime;
    private Integer maxQuantity;
    private Integer cost;
}
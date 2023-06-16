package com.example.tourism_management_system.model.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInTour {

    private Long userId;

    private Long tourId;

    private String carType;

    private int quantity;


}
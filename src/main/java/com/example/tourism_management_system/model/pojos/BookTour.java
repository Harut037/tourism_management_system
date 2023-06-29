package com.example.tourism_management_system.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTour {
    
    private Tour tour;
    private Integer quantity;
}
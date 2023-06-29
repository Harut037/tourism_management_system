package com.example.tourism_management_system.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    
    private String feedback;
    private Integer driver;
    private Integer guide;
    private Integer support;
    private Integer tour;
    private Integer company;
}
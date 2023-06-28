package com.example.tourism_management_system.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveReview {
    
    private Review review;
    private Tour tour;
}
package com.example.tourism_management_system.model.pojos;

import com.example.tourism_management_system.model.entities.ReviewEntity;
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

    public Review(ReviewEntity reviewEntity) {
        this.feedback = reviewEntity.getFeedback();
        this.driver = reviewEntity.getDriver();
        this.guide = reviewEntity.getGuide();
        this.support = reviewEntity.getSupport();
        this.tour = reviewEntity.getTour();
        this.company = reviewEntity.getCompany();
    }
}
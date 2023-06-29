package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name = "review" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedback;
    @Column(name = "driver", nullable = false)
    private Integer driver;
    @Column(name = "guide", nullable = false)
    private Integer guide;
    @Column(name = "support", nullable = false)
    private Integer support;
    @Column(name = "tour", nullable = false)
    private Integer tour;
    @Column(name = "company", nullable = false)
    private Integer company;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;
    
    public ReviewEntity (Review review) {
        this.feedback = review.getFeedback();
        this.driver = review.getDriver();
        this.guide = review.getGuide();
        this.support = review.getSupport();
        this.tour = review.getTour();
        this.company = review.getCompany();
    }
}
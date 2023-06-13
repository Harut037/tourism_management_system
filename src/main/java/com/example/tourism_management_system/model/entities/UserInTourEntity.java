package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.UserInTour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_In_Tour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_Entity")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "Tour")
    private TourEntity tourEntity;
    @OneToOne
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;

    public UserInTourEntity(UserInTour userInTour) {
        this.setUserEntity(new UserEntity(userInTour.getUser()));
        this.setTourEntity(new TourEntity(userInTour.getTour()));
        this.setQuantity(userInTour.getQuantity());
    }
    
}
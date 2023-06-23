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
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    private TourEntity tour;
    @OneToOne
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "transaction_number", nullable = false, unique = true)
    private String transactionNumber;
    @Column(name = "flag", nullable = false)
    private Boolean flag = true;

    public UserInTourEntity(UserInTour userInTour) {
        this.setUser(new UserEntity(userInTour.getUser()));
        this.setTour(new TourEntity(userInTour.getTour()));
        this.setQuantity(userInTour.getQuantity());
    }
    
}
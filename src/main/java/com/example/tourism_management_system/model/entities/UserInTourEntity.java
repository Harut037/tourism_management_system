package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.pojos.UserInTour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_in_tour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInTourEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "tour_id")
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

    public UserInTourEntity(UserInTour userInTour) {
        this.setTransactionNumber(userInTour.getTransactionNumber());
        this.setQuantity(userInTour.getQuantity());
        this.setTour(new TourEntity(userInTour.getTour()));
        this.setPrice((double) this.quantity * this.tour.getCost());
        this.setStatus(Status.ACTIVE);
    }
}
package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.pojos.Card;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.UserInTour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table ( name = "User_Entity" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long              id;
    @Column ( nullable = false, length = 50 )
    private String            firstName;
    @Column ( nullable = false, length = 50 )
    private String            lastName;
    @Column ( nullable = false, length = 50, unique = true )
    private String            email;
    @Column ( nullable = false )
    private Date              birthDate;
    @Column ( nullable = false )
    private String            password;
    @Column ( nullable = false, length = 12, unique = true )
    private String            phoneNumber;
    @Column ( nullable = false )
    private Boolean           flag = true;
    @OneToOne
    @JoinColumn ( name = "role_id" )
    private RoleEntity        roleEntity;
    @OneToMany(mappedBy = "user")
    private List <CardEntity> cardEntities;
    @OneToMany(mappedBy = "user")
    private List <UserInTourEntity> userInTourEntities;
    
    public UserEntity (User user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setBirthDate(user.getBirthDate());
        this.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.setPhoneNumber(user.getPhoneNumber());
        this.setCardEntities(castCards(user.getCards()));
        this.setUserInTourEntities(castUserInTour(user.getUserInTour()));
        //this.setRoleEntity(new RoleEntity(user.getRole()));
    }
    
    public void setPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
    }
    
    private List <UserInTourEntity> castUserInTour (List <UserInTour> userInTour) {
        if ( userInTour != null ){
            List <UserInTourEntity> userInTourEntities = new ArrayList <>();
            userInTour.forEach(userInTourEntity -> userInTourEntities.add(new UserInTourEntity(userInTourEntity)));
            return userInTourEntities;
        }
        return Collections.emptyList();
    }
    
    private List <CardEntity> castCards (List <Card> cards) {
        if (cards != null) {
            List <CardEntity> cardEntities = new ArrayList <>();
            cards.forEach(card -> cardEntities.add(new CardEntity(card)));
            return cardEntities;
        }
        return Collections.emptyList();
    }
}
package com.example.tourism_management_system.model.entities;

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
@Table ( name = "user_entity" )
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
    private RoleEntity              roleEntity;
    @OneToOne
    @JoinColumn(name = "card_id")
    private CardEntityForUser       cardEntityForUser;
    @OneToMany(mappedBy = "user")
    private List <UserInTourEntity> userInTourEntities;
    
    public UserEntity (User user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setBirthDate(user.getBirthDate());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
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
}
package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.pojos.SignUpUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
    @OneToOne
    @JoinColumn(name = "card_id")
    private CardEntityForUser cardEntityForUser;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserInTourEntity> userInTourEntities;

    public UserEntity(SignUpUser signUpUser) {
        this.setFirstName(signUpUser.getFirstName());
        this.setLastName(signUpUser.getLastName());
        this.setEmail(signUpUser.getEmail());
        this.setBirthDate(signUpUser.getBirthDate());
        this.setPassword(signUpUser.getPassword());
        this.setPhoneNumber(signUpUser.getPhoneNumber());
        this.setStatus(Status.ACTIVE);
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
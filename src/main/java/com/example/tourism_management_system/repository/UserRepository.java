package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.EditInfo;
import com.example.tourism_management_system.model.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


   Optional<UserEntity> findByEmail(final String email);


    Optional<UserEntity> findByPhoneNumber(final String phone);

    String forgotPassword(final String email);

    String resetPassword(final String email, final String password);
    
    String updateEmail (String email, String newEmail);
    
    String updatePhoneNumber (String email, String newPhoneNumber);
    
    String updateFirstName (String newFirstName, String email);
    
    String updateLastName (String newLastName, String email);
    
    String updateBirthDate (Date newBirthDate, String email);
}
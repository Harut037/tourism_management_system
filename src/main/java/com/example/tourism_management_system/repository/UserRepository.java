package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {
    
    @Transactional
    @Query ( "SELECT u FROM UserEntity u  WHERE u.email = :email" )
    Optional <UserEntity> findByEmail (final String email);
    
    @Transactional
    @Query ("SELECT u FROM UserEntity u  WHERE u.phoneNumber = :phoneNumber")
    Optional <UserEntity> findByPhoneNumber (final String phoneNumber);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.password = :newPassword WHERE u.email = :email")
    int resetPassword (final String email, final String newPassword);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.email = :newEmail WHERE u.email = :email")
    int updateEmail (String email, String newEmail);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.phoneNumber = :newPhoneNumber WHERE u.email = :email")
    int updatePhoneNumber (String email, String newPhoneNumber);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.firstName = :newFirstName WHERE u.email = :email")
    void updateFirstName (String newFirstName, String email);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.lastName = :newLastName WHERE u.email = :email")
    void updateLastName (String newLastName, String email);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.birthDate = :newBirthDate WHERE u.email = :email")
    void updateBirthDate (LocalDate newBirthDate, String email);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.cardEntityForUser = :newCardEntityForUser WHERE u.email = :email")
    int addCard (CardEntityForUser newCardEntityForUser, String email);
    
    @Transactional
    @Modifying
    @Query ("UPDATE UserEntity u SET u.cardEntityForUser = null WHERE u.email = :email")
    int deleteCard (String email);
}
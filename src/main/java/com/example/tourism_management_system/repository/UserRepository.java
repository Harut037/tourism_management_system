package com.example.tourism_management_system.repository;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCardNumber(final String passportNumber);

    Optional<UserEntity> findByEmail(final String email);

    Optional<UserEntity> findByPhoneNumber(final String phone);

    ResponseEntity<User> signInViaEmail(final String email, final String password);

    ResponseEntity<User> signInViaPhoneNumber(final String phoneNumber, final String password);

    ResponseEntity<User> update(final User userP);

    ResponseEntity<User> forgotPasswordViaEmail(final String email);

    ResponseEntity<User> forgotPasswordViaPhoneNumber(final String phoneNumber);

    ResponseEntity<User> resetPasswordViaEmail(final String email, final String password);

    ResponseEntity<User> resetPasswordViaPhoneNumber(final String phoneNumber, final String password);

}
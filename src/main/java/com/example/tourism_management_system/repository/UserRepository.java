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

    User signInViaEmail(final String email, final String password);

    User signInViaPhoneNumber(final String phoneNumber, final String password);

    User update(final User userP);

    User forgotPasswordViaEmail(final String email);

    User forgotPasswordViaPhoneNumber(final String phoneNumber);

    User resetPasswordViaEmail(final String email, final String password);

    User resetPasswordViaPhoneNumber(final String phoneNumber, final String password);

}
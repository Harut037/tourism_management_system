package com.example.tourism_management_system.repository.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.EditInfo;
import com.example.tourism_management_system.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@Repository
@Transactional ( readOnly = false )
public class UserRepositoryImpl extends SimpleJpaRepository <UserEntity, Long> implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;
    
    @Autowired
    public UserRepositoryImpl (final EntityManager entityManager) {
        super(UserEntity.class, entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    public Optional <UserEntity> findByEmail (final String email) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.email = :email";
        return entityManager
                .createQuery(jpql, UserEntity.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByPhoneNumber (final String phoneNumber) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager
                .createQuery(jpql, UserEntity.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public String forgotPassword (final String email) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            return "User Found";
        }
        throw new IllegalArgumentException("User Not Found By This Email");
    }
    
    @Override
    public String resetPassword (final String email, final String password) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.password = :newPassword" +
                        " WHERE u.email = :email")
                .setParameter("newPassword", password)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Reset Password";
    }
    
    @Override
    public String updateEmail (String email, String newEmail) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.email = :newEmail" +
                        " WHERE u.email = :email")
                .setParameter("newEmail", newEmail)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Updated Email: " + newEmail;
    }
    
    @Override
    public String updatePhoneNumber (String email, String newPhoneNumber) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.phoneNumber = :newPhoneNumber" +
                        " WHERE u.email = :email")
                .setParameter("newPhoneNumber", newPhoneNumber)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Updated Phone Number: " + newPhoneNumber;
    }
    
    @Override
    public String updateFirstName (String newFirstName, String email) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.firstName = :newFirstName" +
                        " WHERE u.email = :email")
                .setParameter("newFirstName", newFirstName)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Updated First Name: " + newFirstName;
    }
    
    @Override
    public String updateLastName (String newLastName, String email) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.lastName = :newLastName" +
                        " WHERE u.email = :email")
                .setParameter("newLastName", newLastName)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Updated Last Name: " + newLastName;
    }
    
    @Override
    public String updateBirthDate (Date newBirthDate, String email) {
        entityManager
                .createQuery("" +
                        " UPDATE UserEntity u SET" +
                        " u.birthDate = :newBirthDate" +
                        " WHERE u.email = :email")
                .setParameter("newBirthDate", newBirthDate)
                .setParameter("email", email)
                .executeUpdate();
        return "Successfully Updated Birth Date: " + newBirthDate;
    }
}
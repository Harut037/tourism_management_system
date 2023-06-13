package com.example.tourism_management_system.repository.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional <UserEntity> findByCardNumber (final String cardNumber) {
        String jpql = "SELECT u FROM UserEntity u inner join CardEntity p WHERE p.cardNumber = :cardNumber";
        return entityManager
                .createQuery(jpql, UserEntity.class)
                .setParameter("cardNumber", cardNumber)
                .getResultList()
                .stream()
                .findFirst();
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
    public User signInViaEmail (final String email, final String password) {
        Optional <UserEntity> user = this.findByEmail(email);
        return getUserResponseEntity(password, user);
    }
    
    @Override
    public User signInViaPhoneNumber (final String phoneNumber, final String password) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        return getUserResponseEntity(password, user);
    }
    
    @Override
    public User forgotPasswordViaEmail (final String email) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            return null;
        } else {
            return null;
        }
    }
    
    @Override
    public User forgotPasswordViaPhoneNumber (final String phoneNumber) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return null;
        } else {
            return null;
        }
    }
    
    @Override
    public User resetPasswordViaEmail (final String email, final String password) {
        Optional <UserEntity> user = this.findByEmail(email);
        return getUserPResponseEntity(password, user);
    }
    
    @Override
    public User resetPasswordViaPhoneNumber (final String phoneNumber, final String password) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        return getUserPResponseEntity(password, user);
    }
    
    public User update (final User user) {
        Optional <UserEntity> userEntity = this.findByCardNumber(user.getCards().get(0).getCardNumber());
        if (userEntity.isPresent()) {
            if (userEntity.get().getEmail() != null) {
                entityManager
                        .createQuery("UPDATE UserEntity u SET u.email = :newemail WHERE u.email = :username")
                        .setParameter("newemail", user.getEmail())
                        .setParameter("email", userEntity.get().getEmail())
                        .executeUpdate();
            }
            if (userEntity.get().getPhoneNumber() != null) {
                entityManager
                        .createQuery("UPDATE UserEntity u SET u.phoneNumber = :newphoneNumber WHERE u.email = :username")
                        .setParameter("newphoneNumber", user.getPhoneNumber())
                        .setParameter("email", userEntity.get().getEmail())
                        .executeUpdate();
            }
            return null;
        }
        return null;
    }
    
    private User getUserPResponseEntity (String password, Optional <UserEntity> user) {
        if (user.isPresent()) {
            entityManager
                    .createQuery("UPDATE UserEntity u SET u.password = :newPassword WHERE u.email = :username")
                    .setParameter("newPassword", password)
                    .setParameter("email", user.get().getEmail())
                    .executeUpdate();
            return null;
        } else {
            return null;
        }
    }
    
    private User getUserResponseEntity (String password, Optional <UserEntity> user) {
        if (user.isPresent()) {
            if (user.get().getPassword().hashCode() == password.hashCode()) {
                return null;
            } else {
                return null;
            }
        }
        return null;
    }
}